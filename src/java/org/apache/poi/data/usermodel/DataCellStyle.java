package org.apache.poi.data.usermodel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 */
public class DataCellStyle implements CellStyle {

    short fmt;
    String fmtString;

    /**
     * get the index within the Workbook (sequence within the collection of ExtnededFormat objects)
     *
     * @return unique index number of the underlying record this style represents (probably you don't care
     * unless you're comparing which one is which)
     */
    @Override
    public short getIndex() {
        return 0;
    }

    /**
     * set the data format (must be a valid format)
     *
     * @param fmt
     */
    @Override
    public void setDataFormat(short fmt) {
        this.fmt = fmt;
    }

    /**
     * get the index of the format
     *
     */
    @Override
    public short getDataFormat() {
        return fmt;
    }

    /**
     * Get the format string
     */
    @Override
    public String getDataFormatString() {
        return fmtString;
    }

    /**
     * Set the format string
     */
    public void setDataFormatString(String fmtString) {
        this.fmtString = fmtString;
    }

    /**
     * set the font for this style
     *
     * @param font a font object created or retreived from the Workbook object
     */
    @Override
    public void setFont(Font font) {

    }

    /**
     * gets the index of the font for this style
     */
    @Override
    public short getFontIndex() {
        return 0;
    }

    /**
     * set the cell's using this style to be hidden
     *
     * @param hidden - whether the cell using this style should be hidden
     */
    @Override
    public void setHidden(boolean hidden) {

    }

    /**
     * get whether the cell's using this style are to be hidden
     *
     * @return hidden - whether the cell using this style should be hidden
     */
    @Override
    public boolean getHidden() {
        return false;
    }

    /**
     * set the cell's using this style to be locked
     *
     * @param locked - whether the cell using this style should be locked
     */
    @Override
    public void setLocked(boolean locked) {

    }

    /**
     * get whether the cell's using this style are to be locked
     *
     * @return hidden - whether the cell using this style should be locked
     */
    @Override
    public boolean getLocked() {
        return false;
    }

    /**
     * set the type of horizontal alignment for the cell
     *
     * @param align - the type of alignment
     * @see #ALIGN_GENERAL
     * @see #ALIGN_LEFT
     * @see #ALIGN_CENTER
     * @see #ALIGN_RIGHT
     * @see #ALIGN_FILL
     * @see #ALIGN_JUSTIFY
     * @see #ALIGN_CENTER_SELECTION
     */
    @Override
    public void setAlignment(short align) {

    }

    /**
     * get the type of horizontal alignment for the cell
     *
     * @return align - the type of alignment
     * @see #ALIGN_GENERAL
     * @see #ALIGN_LEFT
     * @see #ALIGN_CENTER
     * @see #ALIGN_RIGHT
     * @see #ALIGN_FILL
     * @see #ALIGN_JUSTIFY
     * @see #ALIGN_CENTER_SELECTION
     */
    @Override
    public short getAlignment() {
        return 0;
    }

    /**
     * Set whether the text should be wrapped.
     * Setting this flag to <code>true</code> make all content visible
     * whithin a cell by displaying it on multiple lines
     *
     * @param wrapped wrap text or not
     */
    @Override
    public void setWrapText(boolean wrapped) {

    }

    /**
     * get whether the text should be wrapped
     *
     * @return wrap text or not
     */
    @Override
    public boolean getWrapText() {
        return false;
    }

    /**
     * set the type of vertical alignment for the cell
     *
     * @param align the type of alignment
     * @see #VERTICAL_TOP
     * @see #VERTICAL_CENTER
     * @see #VERTICAL_BOTTOM
     * @see #VERTICAL_JUSTIFY
     */
    @Override
    public void setVerticalAlignment(short align) {

    }

    /**
     * get the type of vertical alignment for the cell
     *
     * @return align the type of alignment
     * @see #VERTICAL_TOP
     * @see #VERTICAL_CENTER
     * @see #VERTICAL_BOTTOM
     * @see #VERTICAL_JUSTIFY
     */
    @Override
    public short getVerticalAlignment() {
        return 0;
    }

    /**
     * set the degree of rotation for the text in the cell
     *
     * @param rotation degrees (between -90 and 90 degrees)
     */
    @Override
    public void setRotation(short rotation) {
    }

    /**
     * get the degree of rotation for the text in the cell
     *
     * @return rotation degrees (between -90 and 90 degrees)
     */
    @Override
    public short getRotation() {
        return 0;
    }

    /**
     * set the number of spaces to indent the text in the cell
     *
     * @param indent - number of spaces
     */
    @Override
    public void setIndention(short indent) {
    }

    /**
     * get the number of spaces to indent the text in the cell
     *
     * @return indent - number of spaces
     */
    @Override
    public short getIndention() {
        return 0;
    }

    /**
     * set the type of border to use for the left border of the cell
     *
     * @param border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public void setBorderLeft(short border) {
    }

    /**
     * get the type of border to use for the left border of the cell
     *
     * @return border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public short getBorderLeft() {
        return 0;
    }

    /**
     * set the type of border to use for the right border of the cell
     *
     * @param border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public void setBorderRight(short border) {

    }

    /**
     * get the type of border to use for the right border of the cell
     *
     * @return border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public short getBorderRight() {
        return 0;
    }

    /**
     * set the type of border to use for the top border of the cell
     *
     * @param border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public void setBorderTop(short border) {

    }

    /**
     * get the type of border to use for the top border of the cell
     *
     * @return border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public short getBorderTop() {
        return 0;
    }

    /**
     * set the type of border to use for the bottom border of the cell
     *
     * @param border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public void setBorderBottom(short border) {

    }

    /**
     * get the type of border to use for the bottom border of the cell
     *
     * @return border type
     * @see #BORDER_NONE
     * @see #BORDER_THIN
     * @see #BORDER_MEDIUM
     * @see #BORDER_DASHED
     * @see #BORDER_DOTTED
     * @see #BORDER_THICK
     * @see #BORDER_DOUBLE
     * @see #BORDER_HAIR
     * @see #BORDER_MEDIUM_DASHED
     * @see #BORDER_DASH_DOT
     * @see #BORDER_MEDIUM_DASH_DOT
     * @see #BORDER_DASH_DOT_DOT
     * @see #BORDER_MEDIUM_DASH_DOT_DOT
     * @see #BORDER_SLANTED_DASH_DOT
     */
    @Override
    public short getBorderBottom() {
        return 0;
    }

    /**
     * set the color to use for the left border
     *
     * @param color The index of the color definition
     */
    @Override
    public void setLeftBorderColor(short color) {

    }

    /**
     * get the color to use for the left border
     */
    @Override
    public short getLeftBorderColor() {
        return 0;
    }

    /**
     * set the color to use for the right border
     *
     * @param color The index of the color definition
     */
    @Override
    public void setRightBorderColor(short color) {

    }

    /**
     * get the color to use for the left border
     *
     * @return the index of the color definition
     */
    @Override
    public short getRightBorderColor() {
        return 0;
    }

    /**
     * set the color to use for the top border
     *
     * @param color The index of the color definition
     */
    @Override
    public void setTopBorderColor(short color) {

    }

    /**
     * get the color to use for the top border
     *
     * @return hhe index of the color definition
     */
    @Override
    public short getTopBorderColor() {
        return 0;
    }

    /**
     * set the color to use for the bottom border
     *
     * @param color The index of the color definition
     */
    @Override
    public void setBottomBorderColor(short color) {

    }

    /**
     * get the color to use for the left border
     *
     * @return the index of the color definition
     */
    @Override
    public short getBottomBorderColor() {
        return 0;
    }

    /**
     * setting to one fills the cell with the foreground color... No idea about
     * other values
     *
     * @param fp fill pattern (set to 1 to fill w/foreground color)
     * @see #NO_FILL
     * @see #SOLID_FOREGROUND
     * @see #FINE_DOTS
     * @see #ALT_BARS
     * @see #SPARSE_DOTS
     * @see #THICK_HORZ_BANDS
     * @see #THICK_VERT_BANDS
     * @see #THICK_BACKWARD_DIAG
     * @see #THICK_FORWARD_DIAG
     * @see #BIG_SPOTS
     * @see #BRICKS
     * @see #THIN_HORZ_BANDS
     * @see #THIN_VERT_BANDS
     * @see #THIN_BACKWARD_DIAG
     * @see #THIN_FORWARD_DIAG
     * @see #SQUARES
     * @see #DIAMONDS
     */
    @Override
    public void setFillPattern(short fp) {

    }

    /**
     * get the fill pattern (??) - set to 1 to fill with foreground color
     *
     * @return fill pattern
     */
    @Override
    public short getFillPattern() {
        return 0;
    }

    /**
     * set the background fill color.
     *
     * @param bg color
     */
    @Override
    public void setFillBackgroundColor(short bg) {

    }

    /**
     * get the background fill color, if the fill
     * is defined with an indexed color.
     *
     * @return fill color index, or 0 if not indexed (XSSF only)
     */
    @Override
    public short getFillBackgroundColor() {
        return 0;
    }

    /**
     * Gets the color object representing the current
     * background fill, resolving indexes using
     * the supplied workbook.
     * This will work for both indexed and rgb
     * defined colors.
     */
    @Override
    public Color getFillBackgroundColorColor() {
        return null;
    }

    /**
     * set the foreground fill color
     * <i>Note: Ensure Foreground color is set prior to background color.</i>
     *
     * @param bg color
     */
    @Override
    public void setFillForegroundColor(short bg) {

    }

    /**
     * get the foreground fill color, if the fill
     * is defined with an indexed color.
     *
     * @return fill color, or 0 if not indexed (XSSF only)
     */
    @Override
    public short getFillForegroundColor() {
        return 0;
    }

    /**
     * Gets the color object representing the current
     * foreground fill, resolving indexes using
     * the supplied workbook.
     * This will work for both indexed and rgb
     * defined colors.
     */
    @Override
    public Color getFillForegroundColorColor() {
        return null;
    }

    /**
     * Clones all the style information from another
     * CellStyle, onto this one. This
     * CellStyle will then have all the same
     * properties as the source, but the two may
     * be edited independently.
     * Any stylings on this CellStyle will be lost!
     * <p/>
     * The source CellStyle could be from another
     * Workbook if you like. This allows you to
     * copy styles from one Workbook to another.
     * <p/>
     * However, both of the CellStyles will need
     * to be of the same type (HSSFCellStyle or
     * XSSFCellStyle)
     *
     * @param source
     */
    @Override
    public void cloneStyleFrom(CellStyle source) {
        setDataFormat(source.getDataFormat());
        setDataFormatString(source.getDataFormatString());
    }

    /**
     * Controls if the Cell should be auto-sized
     * to shrink to fit if the text is too long
     *
     * @param shrinkToFit
     */
    @Override
    public void setShrinkToFit(boolean shrinkToFit) {

    }

    /**
     * Should the Cell be auto-sized by Excel to shrink
     * it to fit if this text is too long?
     */
    @Override
    public boolean getShrinkToFit() {
        return false;
    }
}
