package org.apache.poi.data.usermodel;

import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class DataWorkbook implements Workbook {

    List<DataSheet> sheets;
    Map<String,DataSheet> sheetMap = new HashMap<String,DataSheet>();
    Map<String,Integer> indexMap = new HashMap<String,Integer>();


    public void addSheet(DataSheet sheet){
        sheetMap.put(sheet.getSheetName(),sheet);
        sheets.add(sheet);
        indexMap.put(sheet.getSheetName(), sheets.size() - 1);
    }

    /**
     * Convenience method to get the active sheet.  The active sheet is is the sheet
     * which is currently displayed when the workbook is viewed in Excel.
     * 'Selected' sheet(s) is a distinct concept.
     *
     * @return the index of the active sheet (0-based)
     */
    @Override
    public int getActiveSheetIndex() {
        return 0;
    }

    /**
     * Convenience method to set the active sheet.  The active sheet is is the sheet
     * which is currently displayed when the workbook is viewed in Excel.
     * 'Selected' sheet(s) is a distinct concept.
     *
     * @param sheetIndex index of the active sheet (0-based)
     */
    @Override
    public void setActiveSheet(int sheetIndex) {
    }

    /**
     * Gets the first tab that is displayed in the list of tabs in excel.
     *
     * @return the first tab that to display in the list of tabs (0-based).
     */
    @Override
    public int getFirstVisibleTab() {
        return 0;
    }

    /**
     * Sets the first tab that is displayed in the list of tabs in excel.
     *
     * @param sheetIndex the first tab that to display in the list of tabs (0-based)
     */
    @Override
    public void setFirstVisibleTab(int sheetIndex) {

    }

    /**
     * Sets the order of appearance for a given sheet.
     *
     * @param sheetname the name of the sheet to reorder
     * @param pos       the position that we want to insert the sheet into (0 based)
     */
    @Override
    public void setSheetOrder(String sheetname, int pos) {

    }

    /**
     * Sets the tab whose data is actually seen when the sheet is opened.
     * This may be different from the "selected sheet" since excel seems to
     * allow you to show the data of one sheet when another is seen "selected"
     * in the tabs (at the bottom).
     *
     * @param index the index of the sheet to select (0 based)
     * @see Sheet#setSelected(boolean)
     */
    @Override
    public void setSelectedTab(int index) {

    }

    /**
     * Set the sheet name.
     * <p>
     * See {@link org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
     * for a safe way to create valid names
     * </p>
     *
     * @param sheet number (0 based)
     * @param name
     * @throws IllegalArgumentException if the name is null or invalid
     *                                  or workbook already contains a sheet with this name
     * @see #createSheet(String)
     * @see org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)
     */
    @Override
    public void setSheetName(int sheet, String name) {
    }

    /**
     * Get the sheet name
     *
     * @param sheet sheet number (0 based)
     * @return Sheet name
     */
    @Override
    public String getSheetName(int sheet) {
        return sheets.get(sheet).getSheetName();
    }

    /**
     * Returns the index of the sheet by his name
     *
     * @param name the sheet name
     * @return index of the sheet (0 based)
     */
    @Override
    public int getSheetIndex(String name) {
        Integer index = indexMap.get(name);
        if(index==null){
            return -1;
        }else{
            return index;
        }
    }

    /**
     * Returns the index of the given sheet
     *
     * @param sheet the sheet to look up
     * @return index of the sheet (0 based)
     */
    @Override
    public int getSheetIndex(Sheet sheet) {
        return getSheetIndex(sheet.getSheetName());
    }

    /**
     * Create an Sheet for this Workbook, adds it to the sheets and returns
     * the high level representation.  Use this to create new sheets.
     *
     * @return Sheet representing the new sheet.
     */
    @Override
    public Sheet createSheet() {
        return null;
    }

    /**
     * Create a new sheet for this Workbook and return the high level representation.
     * Use this to create new sheets.
     * <p/>
     * <p>
     * Note that Excel allows sheet names up to 31 chars in length but other applications
     * (such as OpenOffice) allow more. Some versions of Excel crash with names longer than 31 chars,
     * others - truncate such names to 31 character.
     * </p>
     * <p>
     * POI's SpreadsheetAPI silently truncates the input argument to 31 characters.
     * Example:
     * <p/>
     * <pre><code>
     *     Sheet sheet = workbook.createSheet("My very long sheet name which is longer than 31 chars"); // will be truncated
     *     assert 31 == sheet.getSheetName().length();
     *     assert "My very long sheet name which i" == sheet.getSheetName();
     *     </code></pre>
     * </p>
     * <p/>
     * Except the 31-character constraint, Excel applies some other rules:
     * <p>
     * Sheet name MUST be unique in the workbook and MUST NOT contain the any of the following characters:
     * <ul>
     * <li> 0x0000 </li>
     * <li> 0x0003 </li>
     * <li> colon (:) </li>
     * <li> backslash (\) </li>
     * <li> asterisk (*) </li>
     * <li> question mark (?) </li>
     * <li> forward slash (/) </li>
     * <li> opening square bracket ([) </li>
     * <li> closing square bracket (]) </li>
     * </ul>
     * The string MUST NOT begin or end with the single quote (') character.
     * </p>
     * <p/>
     * <p>
     * See {@link org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
     * for a safe way to create valid names
     * </p>
     *
     * @param sheetname sheetname to set for the sheet.
     * @return Sheet representing the new sheet.
     * @throws IllegalArgumentException if the name is null or invalid
     *                                  or workbook already contains a sheet with this name
     * @see org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)
     */
    @Override
    public Sheet createSheet(String sheetname) {
        return null;
    }

    /**
     * Create an Sheet from an existing sheet in the Workbook.
     *
     * @param sheetNum
     * @return Sheet representing the cloned sheet.
     */
    @Override
    public Sheet cloneSheet(int sheetNum) {
        return null;
    }

    /**
     * Get the number of spreadsheets in the workbook
     *
     * @return the number of sheets
     */
    @Override
    public int getNumberOfSheets() {
        return sheets.size();
    }

    /**
     * Get the Sheet object at the given index.
     *
     * @param index of the sheet number (0-based physical & logical)
     * @return Sheet at the provided index
     */
    @Override
    public Sheet getSheetAt(int index) {
        return sheets.get(index);
    }

    /**
     * Get sheet with the given name
     *
     * @param name of the sheet
     * @return Sheet with the name provided or <code>null</code> if it does not exist
     */
    @Override
    public Sheet getSheet(String name) {
        return sheetMap.get(name);
    }

    /**
     * Removes sheet at the given index
     *
     * @param index of the sheet to remove (0-based)
     */
    @Override
    public void removeSheetAt(int index) {
        Sheet sheet = sheets.get(index);
        sheets.remove(index);
        indexMap.remove(index);
        sheetMap.remove(sheet.getSheetName());
    }

    /**
     * Sets the repeating rows and columns for a sheet (as found in
     * File->PageSetup->Sheet).  This is function is included in the workbook
     * because it creates/modifies name records which are stored at the
     * workbook level.
     * <p/>
     * To set just repeating columns:
     * <pre>
     *  workbook.setRepeatingRowsAndColumns(0,0,1,-1-1);
     * </pre>
     * To set just repeating rows:
     * <pre>
     *  workbook.setRepeatingRowsAndColumns(0,-1,-1,0,4);
     * </pre>
     * To remove all repeating rows and columns for a sheet.
     * <pre>
     *  workbook.setRepeatingRowsAndColumns(0,-1,-1,-1,-1);
     * </pre>
     *
     * @param sheetIndex  0 based index to sheet.
     * @param startColumn 0 based start of repeating columns.
     * @param endColumn   0 based end of repeating columns.
     * @param startRow    0 based start of repeating rows.
     * @param endRow      0 based end of repeating rows.
     */
    @Override
    public void setRepeatingRowsAndColumns(int sheetIndex, int startColumn, int endColumn, int startRow, int endRow) {

    }

    /**
     * Create a new Font and add it to the workbook's font table
     *
     * @return new font object
     */
    @Override
    public Font createFont() {
        return null;
    }

    /**
     * Finds a font that matches the one with the supplied attributes
     *
     * @param boldWeight
     * @param color
     * @param fontHeight
     * @param name
     * @param italic
     * @param strikeout
     * @param typeOffset
     * @param underline
     * @return the font with the matched attributes or <code>null</code>
     */
    @Override
    public Font findFont(short boldWeight, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        return null;
    }

    /**
     * Get the number of fonts in the font table
     *
     * @return number of fonts
     */
    @Override
    public short getNumberOfFonts() {
        return 0;
    }

    /**
     * Get the font at the given index number
     *
     * @param idx index number (0-based)
     * @return font at the index
     */
    @Override
    public Font getFontAt(short idx) {
        return null;
    }

    /**
     * Create a new Cell style and add it to the workbook's style table
     *
     * @return the new Cell Style object
     */
    @Override
    public CellStyle createCellStyle() {
        return null;
    }

    /**
     * Get the number of styles the workbook contains
     *
     * @return count of cell styles
     */
    @Override
    public short getNumCellStyles() {
        return 0;
    }

    /**
     * Get the cell style object at the given index
     *
     * @param idx index within the set of styles (0-based)
     * @return CellStyle object at the index
     */
    @Override
    public CellStyle getCellStyleAt(short idx) {
        return null;
    }

    /**
     * Write out this workbook to an Outputstream.
     *
     * @param stream - the java OutputStream you wish to write to
     * @throws java.io.IOException if anything can't be written.
     */
    @Override
    public void write(OutputStream stream) throws IOException {

    }

    /**
     * Close the underlying input resource (File or Stream),
     * from which the Workbook was read. After closing, the
     * Workbook should no longer be used.
     * <p>This will have no effect newly created Workbooks.
     */
    @Override
    public void close() throws IOException {

    }

    /**
     * @return the total number of defined names in this workbook
     */
    @Override
    public int getNumberOfNames() {
        return 0;
    }

    /**
     * @param name the name of the defined name
     * @return the defined name with the specified name. <code>null</code> if not found.
     */
    @Override
    public Name getName(String name) {
        return null;
    }

    /**
     * @param nameIndex position of the named range (0-based)
     * @return the defined name at the specified index
     * @throws IllegalArgumentException if the supplied index is invalid
     */
    @Override
    public Name getNameAt(int nameIndex) {
        return null;
    }

    /**
     * Creates a new (uninitialised) defined name in this workbook
     *
     * @return new defined name object
     */
    @Override
    public Name createName() {
        return null;
    }

    /**
     * Gets the defined name index by name<br/>
     * <i>Note:</i> Excel defined names are case-insensitive and
     * this method performs a case-insensitive search.
     *
     * @param name the name of the defined name
     * @return zero based index of the defined name. <tt>-1</tt> if not found.
     */
    @Override
    public int getNameIndex(String name) {
        return 0;
    }

    /**
     * Remove the defined name at the specified index
     *
     * @param index named range index (0 based)
     */
    @Override
    public void removeName(int index) {

    }

    /**
     * Remove a defined name by name
     *
     * @param name the name of the defined name
     */
    @Override
    public void removeName(String name) {

    }

    /**
     * Sets the printarea for the sheet provided
     * <p/>
     * i.e. Reference = $A$1:$B$2
     *
     * @param sheetIndex Zero-based sheet index (0 Represents the first sheet to keep consistent with java)
     * @param reference  Valid name Reference for the Print Area
     */
    @Override
    public void setPrintArea(int sheetIndex, String reference) {

    }

    /**
     * For the Convenience of Java Programmers maintaining pointers.
     *
     * @param sheetIndex  Zero-based sheet index (0 = First Sheet)
     * @param startColumn Column to begin printarea
     * @param endColumn   Column to end the printarea
     * @param startRow    Row to begin the printarea
     * @param endRow      Row to end the printarea
     * @see #setPrintArea(int, String)
     */
    @Override
    public void setPrintArea(int sheetIndex, int startColumn, int endColumn, int startRow, int endRow) {

    }

    /**
     * Retrieves the reference for the printarea of the specified sheet,
     * the sheet name is appended to the reference even if it was not specified.
     *
     * @param sheetIndex Zero-based sheet index (0 Represents the first sheet to keep consistent with java)
     * @return String Null if no print area has been defined
     */
    @Override
    public String getPrintArea(int sheetIndex) {
        return null;
    }

    /**
     * Delete the printarea for the sheet specified
     *
     * @param sheetIndex Zero-based sheet index (0 = First Sheet)
     */
    @Override
    public void removePrintArea(int sheetIndex) {

    }

    /**
     * Retrieves the current policy on what to do when
     * getting missing or blank cells from a row.
     * <p>
     * The default is to return blank and null cells.
     * </p>
     */
    @Override
    public Row.MissingCellPolicy getMissingCellPolicy() {
        return null;
    }

    /**
     * Sets the policy on what to do when
     * getting missing or blank cells from a row.
     * <p/>
     * This will then apply to all calls to
     * {@link org.apache.poi.ss.usermodel.Row#getCell(int)} }. See
     *
     * @param missingCellPolicy
     */
    @Override
    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {

    }

    /**
     * Returns the instance of DataFormat for this workbook.
     *
     * @return the DataFormat object
     */
    @Override
    public DataFormat createDataFormat() {
        return null;
    }

    /**
     * Adds a picture to the workbook.
     *
     * @param pictureData The bytes of the picture
     * @param format      The format of the picture.
     * @return the index to this picture (1 based).
     * @see #PICTURE_TYPE_EMF
     * @see #PICTURE_TYPE_WMF
     * @see #PICTURE_TYPE_PICT
     * @see #PICTURE_TYPE_JPEG
     * @see #PICTURE_TYPE_PNG
     * @see #PICTURE_TYPE_DIB
     */
    @Override
    public int addPicture(byte[] pictureData, int format) {
        return 0;
    }

    /**
     * Gets all pictures from the Workbook.
     *
     * @return the list of pictures (a list of {@link org.apache.poi.ss.usermodel.PictureData} objects.)
     */
    @Override
    public List<? extends PictureData> getAllPictures() {
        return null;
    }

    /**
     * Returns an object that handles instantiating concrete
     * classes of the various instances one needs for  HSSF and XSSF.
     */
    @Override
    public CreationHelper getCreationHelper() {
        return null;
    }

    /**
     * @return <code>false</code> if this workbook is not visible in the GUI
     */
    @Override
    public boolean isHidden() {
        return false;
    }

    /**
     * @param hiddenFlag pass <code>false</code> to make the workbook visible in the GUI
     */
    @Override
    public void setHidden(boolean hiddenFlag) {

    }

    /**
     * Check whether a sheet is hidden.
     * <p>
     * Note that a sheet could instead be set to be very hidden, which is different
     * ({@link #isSheetVeryHidden(int)})
     * </p>
     *
     * @param sheetIx Number
     * @return <code>true</code> if sheet is hidden
     */
    @Override
    public boolean isSheetHidden(int sheetIx) {
        return false;
    }

    /**
     * Check whether a sheet is very hidden.
     * <p>
     * This is different from the normal hidden status
     * ({@link #isSheetHidden(int)})
     * </p>
     *
     * @param sheetIx sheet index to check
     * @return <code>true</code> if sheet is very hidden
     */
    @Override
    public boolean isSheetVeryHidden(int sheetIx) {
        return false;
    }

    /**
     * Hide or unhide a sheet
     *
     * @param sheetIx the sheet index (0-based)
     * @param hidden  True to mark the sheet as hidden, false otherwise
     */
    @Override
    public void setSheetHidden(int sheetIx, boolean hidden) {

    }

    /**
     * Hide or unhide a sheet.
     * <p/>
     * <ul>
     * <li>0 - visible. </li>
     * <li>1 - hidden. </li>
     * <li>2 - very hidden.</li>
     * </ul>
     *
     * @param sheetIx the sheet index (0-based)
     * @param hidden  one of the following <code>Workbook</code> constants:
     *                <code>Workbook.SHEET_STATE_VISIBLE</code>,
     *                <code>Workbook.SHEET_STATE_HIDDEN</code>, or
     *                <code>Workbook.SHEET_STATE_VERY_HIDDEN</code>.
     * @throws IllegalArgumentException if the supplied sheet index or state is invalid
     */
    @Override
    public void setSheetHidden(int sheetIx, int hidden) {

    }

    /**
     * Register a new toolpack in this workbook.
     *
     * @param toopack the toolpack to register
     */
    @Override
    public void addToolPack(UDFFinder toopack) {

    }

    /**
     * Whether the application shall perform a full recalculation when the workbook is opened.
     * <p>
     * Typically you want to force formula recalculation when you modify cell formulas or values
     * of a workbook previously created by Excel. When set to true, this flag will tell Excel
     * that it needs to recalculate all formulas in the workbook the next time the file is opened.
     * </p>
     * <p>
     * Note, that recalculation updates cached formula results and, thus, modifies the workbook.
     * Depending on the version, Excel may prompt you with "Do you want to save the changes in <em>filename</em>?"
     * on close.
     * </p>
     *
     * @param value true if the application will perform a full recalculation of
     *              workbook values when the workbook is opened
     * @since 3.8
     */
    @Override
    public void setForceFormulaRecalculation(boolean value) {

    }

    /**
     * Whether Excel will be asked to recalculate all formulas when the  workbook is opened.
     *
     * @since 3.8
     */
    @Override
    public boolean getForceFormulaRecalculation() {
        return false;
    }
}
