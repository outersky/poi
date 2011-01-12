/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.hmef;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hsmf.datatypes.MAPIProperty;
import org.apache.poi.hsmf.datatypes.Types;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

/**
 * A pure-MAPI attribute which applies to a {@link HMEFMessage}
 *  or one of its {@link Attachment}s.
 */
public class MAPIAttribute {
   private final MAPIProperty property;
   private final int type;
   private final byte[] data;
   
   /**
    * Constructs a single new attribute from
    *  the contents of the stream
    */
   public MAPIAttribute(MAPIProperty property, int type, byte[] data) {
      this.property = property;
      this.type = type;
      this.data = data;
   }

   public MAPIProperty getProperty() {
      return property;
   }

   public int getType() {
      return type;
   }

   public byte[] getData() {
      return data;
   }
   
   public String toString() {
      return property.toString() + " " + HexDump.toHex(data);
   }
   
   /**
    * Parses a MAPI Properties TNEF Attribute, and returns
    *  the list of MAPI Attributes contained within it
    */
   public static List<MAPIAttribute> create(Attribute parent) throws IOException {
      if(parent.getId() != Attribute.ID_MAPIPROPERTIES) {
         throw new IllegalArgumentException(
               "Can only create from a MAPIProperty attribute, " +
               "instead received a " + parent.getId() + " one"
         );
      }
      ByteArrayInputStream inp = new ByteArrayInputStream(parent.getData());
      
      // First up, get the number of attributes
      int count = LittleEndian.readInt(inp);
      List<MAPIAttribute> attrs = new ArrayList<MAPIAttribute>();
      
      // Now, read each one in in turn
      for(int i=0; i<count; i++) {
         int typeAndMV = LittleEndian.readUShort(inp);
         int id = LittleEndian.readUShort(inp);
         
         // Is it either Multi-Valued or Variable-Length?
         boolean isMV = false;
         boolean isVL = false;
         int type = typeAndMV;
         if( (typeAndMV & Types.MULTIVALUED_FLAG) > 0 ) {
            isMV = true;
            type -= Types.MULTIVALUED_FLAG;
         }
         if(type == Types.ASCII_STRING || type == Types.UNICODE_STRING ||
               type == Types.BINARY || type == Types.DIRECTORY) {
            isVL = true;
         }
         
         // If it's a named property, rather than a standard
         //  MAPI property, grab the details of it
         MAPIProperty prop = MAPIProperty.get(id);
         if(id >= 0x8000 && id <= 0xFFFF) {
            // TODO
            throw new UnsupportedOperationException("Not yet implemented for id " + id);
         }
         
         // Now read in the value(s)
         int values = 1;
         if(isMV || isVL) {
            values = LittleEndian.readInt(inp);
         }
         for(int j=0; j<values; j++) {
            int len = getLength(type, inp);
            byte[] data = new byte[len];
            IOUtils.readFully(inp, data);
            
            // Create
            MAPIAttribute attr;
            if(type == Types.UNICODE_STRING || type == Types.ASCII_STRING) {
               attr = new MAPIStringAttribute(prop, type, data);
            } else {
               attr = new MAPIAttribute(prop, type, data);
            }
            attrs.add(attr);
            
            // Data is always padded out to a 4 byte boundary
            if(len % 4 != 0) {
               byte[] padding = new byte[len % 4];
               IOUtils.readFully(inp, padding);
            }
         }
         break;
      }
      
      // All done
      return attrs;
   }
   private static int getLength(int type, InputStream inp) throws IOException {
      switch(type) {
         case Types.NULL:
            return 0;
         case Types.BOOLEAN:
         case Types.SHORT:
            return 2;
         case Types.LONG:
         case Types.FLOAT:
         case Types.ERROR:
            return 4;
         case Types.LONG_LONG:
         case Types.DOUBLE:
         case Types.APP_TIME:
         case Types.TIME:
         case Types.CURRENCY:
            return 8;
         case Types.CLS_ID:
            return 16;
         case Types.ASCII_STRING:
         case Types.UNICODE_STRING:
         case Types.DIRECTORY:
         case Types.BINARY:
            // Need to read the length, as it varies
            return LittleEndian.readInt(inp);
         default:
            throw new IllegalArgumentException("Unknown type " + type);
      }
   }
}