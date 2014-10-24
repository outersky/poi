package org.apache.poi.data.usermodel;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.poi.hssf.util.PaneInformation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Iterator;

/**
 *
 */
public class DataSheet implements Sheet {
    Workbook workbook;
    String sheetName;
    DataRow[] rows;

    public void cloneFromSheet(Sheet sheet){
        workbook = sheet.getWorkbook();
        sheetName = sheet.getSheetName();

        int maxIndex = sheet.getLastRowNum();
        rows = new DataRow[maxIndex+1];
        for(Row row : sheet){
            DataRow newRow = new DataRow(this);
            newRow.cloneFromRow(row);
            rows[row.getRowNum()] = newRow;
        }

    }

    /**
     * Create a new row within the sheet and return the high level representation
     *
     * @param rownum row number
     * @return high level Row object representing a row in the sheet
     * @see #removeRow(org.apache.poi.ss.usermodel.Row)
     */
    @Override
    public Row createRow(int rownum) {
        return null;
    }

    /**
     * Remove a row from this sheet.  All cells contained in the row are removed as well
     *
     * @param row representing a row to remove.
     */
    @Override
    public void removeRow(Row row) {
    }

    /**
     * Returns the logical row (not physical) 0-based.  If you ask for a row that is not
     * defined you get a null.  This is to say row 4 represents the fifth row on a sheet.
     *
     * @param rownum row to get (0-based)
     * @return Row representing the rownumber or null if its not defined on the sheet
     */
    @Override
    public Row getRow(int rownum) {
        return rows[rownum];
    }

    /**
     * Returns the number of physically defined rows (NOT the number of rows in the sheet)
     *
     * @return the number of physically defined rows in this sheet
     */
    @Override
    public int getPhysicalNumberOfRows() {
        return 0;
    }

    /**
     * Gets the first row on the sheet
     *
     * @return the number of the first logical row on the sheet (0-based)
     */
    @Override
    public int getFirstRowNum() {
        if(rows!=null && rows.length>0){
            return rows[0].getRowNum();
        }
        return -1;
    }

    /**
     * Gets the last row on the sheet
     *
     * @return last row contained n this sheet (0-based)
     */
    @Override
    public int getLastRowNum() {
        if(rows!=null && rows.length>0){
            return rows[rows.length-1].getRowNum();
        }
        return -1;
    }

    /**
     * Get the visibility state for a given column
     *
     * @param columnIndex - the column to get (0-based)
     * @param hidden      - the visiblity state of the column
     */
    @Override
    public void setColumnHidden(int columnIndex, boolean hidden) {
    }

    /**
     * Get the hidden state for a given column
     *
     * @param columnIndex - the column to set (0-based)
     * @return hidden - <code>false</code> if the column is visible
     */
    @Override
    public boolean isColumnHidden(int columnIndex) {
        return false;
    }

    /**
     * Sets whether the worksheet is displayed from right to left instead of from left to right.
     *
     * @param value true for right to left, false otherwise.
     */
    @Override
    public void setRightToLeft(boolean value) {
    }

    /**
     * Whether the text is displayed in right-to-left mode in the window
     *
     * @return whether the text is displayed in right-to-left mode in the window
     */
    @Override
    public boolean isRightToLeft() {
        return false;
    }

    /**
     * Set the width (in units of 1/256th of a character width)
     * <p/>
     * <p>
     * The maximum column width for an individual cell is 255 characters.
     * This value represents the number of characters that can be displayed
     * in a cell that is formatted with the standard font (first font in the workbook).
     * </p>
     * <p/>
     * <p>
     * Character width is defined as the maximum digit width
     * of the numbers <code>0, 1, 2, ... 9</code> as rendered
     * using the default font (first font in the workbook).
     * <br/>
     * Unless you are using a very special font, the default character is '0' (zero),
     * this is true for Arial (default font font in HSSF) and Calibri (default font in XSSF)
     * </p>
     * <p/>
     * <p>
     * Please note, that the width set by this method includes 4 pixels of margin padding (two on each side),
     * plus 1 pixel padding for the gridlines (Section 3.3.1.12 of the OOXML spec).
     * This results is a slightly less value of visible characters than passed to this method (approx. 1/2 of a character).
     * </p>
     * <p>
     * To compute the actual number of visible characters,
     * Excel uses the following formula (Section 3.3.1.12 of the OOXML spec):
     * </p>
     * <code>
     * width = Truncate([{Number of Visible Characters} *
     * {Maximum Digit Width} + {5 pixel padding}]/{Maximum Digit Width}*256)/256
     * </code>
     * <p>Using the Calibri font as an example, the maximum digit width of 11 point font size is 7 pixels (at 96 dpi).
     * If you set a column width to be eight characters wide, e.g. <code>setColumnWidth(columnIndex, 8*256)</code>,
     * then the actual value of visible characters (the value shown in Excel) is derived from the following equation:
     * <code>
     * Truncate([numChars*7+5]/7*256)/256 = 8;
     * </code>
     * <p/>
     * which gives <code>7.29</code>.
     *
     * @param columnIndex - the column to set (0-based)
     * @param width       - the width in units of 1/256th of a character width
     * @throws IllegalArgumentException if width > 255*256 (the maximum column width in Excel is 255 characters)
     */
    @Override
    public void setColumnWidth(int columnIndex, int width) {

    }

    /**
     * get the width (in units of 1/256th of a character width )
     * <p/>
     * <p>
     * Character width is defined as the maximum digit width
     * of the numbers <code>0, 1, 2, ... 9</code> as rendered
     * using the default font (first font in the workbook)
     * </p>
     *
     * @param columnIndex - the column to set (0-based)
     * @return width - the width in units of 1/256th of a character width
     */
    @Override
    public int getColumnWidth(int columnIndex) {
        return 0;
    }

    /**
     * Set the default column width for the sheet (if the columns do not define their own width)
     * in characters
     *
     * @param width default column width measured in characters
     */
    @Override
    public void setDefaultColumnWidth(int width) {

    }

    /**
     * Get the default column width for the sheet (if the columns do not define their own width)
     * in characters
     *
     * @return default column width measured in characters
     */
    @Override
    public int getDefaultColumnWidth() {
        return 0;
    }

    /**
     * Get the default row height for the sheet (if the rows do not define their own height) in
     * twips (1/20 of  a point)
     *
     * @return default row height measured in twips (1/20 of  a point)
     */
    @Override
    public short getDefaultRowHeight() {
        return 0;
    }

    /**
     * Get the default row height for the sheet (if the rows do not define their own height) in
     * points.
     *
     * @return default row height in points
     */
    @Override
    public float getDefaultRowHeightInPoints() {
        return 0;
    }

    /**
     * Set the default row height for the sheet (if the rows do not define their own height) in
     * twips (1/20 of  a point)
     *
     * @param height default row height measured in twips (1/20 of  a point)
     */
    @Override
    public void setDefaultRowHeight(short height) {

    }

    /**
     * Set the default row height for the sheet (if the rows do not define their own height) in
     * points
     *
     * @param height default row height
     */
    @Override
    public void setDefaultRowHeightInPoints(float height) {

    }

    /**
     * Returns the CellStyle that applies to the given
     * (0 based) column, or null if no style has been
     * set for that column
     *
     * @param column
     */
    @Override
    public CellStyle getColumnStyle(int column) {
        return null;
    }

    /**
     * Adds a merged region of cells (hence those cells form one)
     *
     * @param region (rowfrom/colfrom-rowto/colto) to merge
     * @return index of this region
     */
    @Override
    public int addMergedRegion(CellRangeAddress region) {
        return 0;
    }

    /**
     * Determines whether the output is vertically centered on the page.
     *
     * @param value true to vertically center, false otherwise.
     */
    @Override
    public void setVerticallyCenter(boolean value) {

    }

    /**
     * Determines whether the output is horizontally centered on the page.
     *
     * @param value true to horizontally center, false otherwise.
     */
    @Override
    public void setHorizontallyCenter(boolean value) {

    }

    /**
     * Determine whether printed output for this sheet will be horizontally centered.
     */
    @Override
    public boolean getHorizontallyCenter() {
        return false;
    }

    /**
     * Determine whether printed output for this sheet will be vertically centered.
     */
    @Override
    public boolean getVerticallyCenter() {
        return false;
    }

    /**
     * Removes a merged region of cells (hence letting them free)
     *
     * @param index of the region to unmerge
     */
    @Override
    public void removeMergedRegion(int index) {

    }

    /**
     * Returns the number of merged regions
     *
     * @return number of merged regions
     */
    @Override
    public int getNumMergedRegions() {
        return 0;
    }

    /**
     * Returns the merged region at the specified index
     *
     * @param index
     * @return the merged region at the specified index
     */
    @Override
    public CellRangeAddress getMergedRegion(int index) {
        return null;
    }

    /**
     * Returns an iterator of the physical rows
     *
     * @return an iterator of the PHYSICAL rows.  Meaning the 3rd element may not
     * be the third row if say for instance the second row is undefined.
     */
    @Override
    public Iterator<Row> rowIterator() {
        return new ArrayIterator(rows);
    }

    /**
     * Control if Excel should be asked to recalculate all formulas on this sheet
     * when the workbook is opened.
     * <p/>
     * <p>
     * Calculating the formula values with {@link FormulaEvaluator} is the
     * recommended solution, but this may be used for certain cases where
     * evaluation in POI is not possible.
     * </p>
     * <p/>
     * To force recalcuation of formulas in the entire workbook
     * use {@link Workbook#setForceFormulaRecalculation(boolean)} instead.
     *
     * @param value true if the application will perform a full recalculation of
     *              this worksheet values when the workbook is opened
     * @see Workbook#setForceFormulaRecalculation(boolean)
     */
    @Override
    public void setForceFormulaRecalculation(boolean value) {

    }

    /**
     * Whether Excel will be asked to recalculate all formulas in this sheet when the
     * workbook is opened.
     */
    @Override
    public boolean getForceFormulaRecalculation() {
        return false;
    }

    /**
     * Flag indicating whether the sheet displays Automatic Page Breaks.
     *
     * @param value <code>true</code> if the sheet displays Automatic Page Breaks.
     */
    @Override
    public void setAutobreaks(boolean value) {

    }

    /**
     * Set whether to display the guts or not
     *
     * @param value - guts or no guts
     */
    @Override
    public void setDisplayGuts(boolean value) {

    }

    /**
     * Set whether the window should show 0 (zero) in cells containing zero value.
     * When false, cells with zero value appear blank instead of showing the number zero.
     *
     * @param value whether to display or hide all zero values on the worksheet
     */
    @Override
    public void setDisplayZeros(boolean value) {

    }

    /**
     * Gets the flag indicating whether the window should show 0 (zero) in cells containing zero value.
     * When false, cells with zero value appear blank instead of showing the number zero.
     *
     * @return whether all zero values on the worksheet are displayed
     */
    @Override
    public boolean isDisplayZeros() {
        return false;
    }

    /**
     * Flag indicating whether the Fit to Page print option is enabled.
     *
     * @param value <code>true</code> if the Fit to Page print option is enabled.
     */
    @Override
    public void setFitToPage(boolean value) {

    }

    /**
     * Flag indicating whether summary rows appear below detail in an outline, when applying an outline.
     * <p/>
     * <p>
     * When true a summary row is inserted below the detailed data being summarized and a
     * new outline level is established on that row.
     * </p>
     * <p>
     * When false a summary row is inserted above the detailed data being summarized and a new outline level
     * is established on that row.
     * </p>
     *
     * @param value <code>true</code> if row summaries appear below detail in the outline
     */
    @Override
    public void setRowSumsBelow(boolean value) {

    }

    /**
     * Flag indicating whether summary columns appear to the right of detail in an outline, when applying an outline.
     * <p/>
     * <p>
     * When true a summary column is inserted to the right of the detailed data being summarized
     * and a new outline level is established on that column.
     * </p>
     * <p>
     * When false a summary column is inserted to the left of the detailed data being
     * summarized and a new outline level is established on that column.
     * </p>
     *
     * @param value <code>true</code> if col summaries appear right of the detail in the outline
     */
    @Override
    public void setRowSumsRight(boolean value) {

    }

    /**
     * Flag indicating whether the sheet displays Automatic Page Breaks.
     *
     * @return <code>true</code> if the sheet displays Automatic Page Breaks.
     */
    @Override
    public boolean getAutobreaks() {
        return false;
    }

    /**
     * Get whether to display the guts or not,
     * default value is true
     *
     * @return boolean - guts or no guts
     */
    @Override
    public boolean getDisplayGuts() {
        return false;
    }

    /**
     * Flag indicating whether the Fit to Page print option is enabled.
     *
     * @return <code>true</code> if the Fit to Page print option is enabled.
     */
    @Override
    public boolean getFitToPage() {
        return false;
    }

    /**
     * Flag indicating whether summary rows appear below detail in an outline, when applying an outline.
     * <p/>
     * <p>
     * When true a summary row is inserted below the detailed data being summarized and a
     * new outline level is established on that row.
     * </p>
     * <p>
     * When false a summary row is inserted above the detailed data being summarized and a new outline level
     * is established on that row.
     * </p>
     *
     * @return <code>true</code> if row summaries appear below detail in the outline
     */
    @Override
    public boolean getRowSumsBelow() {
        return false;
    }

    /**
     * Flag indicating whether summary columns appear to the right of detail in an outline, when applying an outline.
     * <p/>
     * <p>
     * When true a summary column is inserted to the right of the detailed data being summarized
     * and a new outline level is established on that column.
     * </p>
     * <p>
     * When false a summary column is inserted to the left of the detailed data being
     * summarized and a new outline level is established on that column.
     * </p>
     *
     * @return <code>true</code> if col summaries appear right of the detail in the outline
     */
    @Override
    public boolean getRowSumsRight() {
        return false;
    }

    /**
     * Gets the flag indicating whether this sheet displays the lines
     * between rows and columns to make editing and reading easier.
     *
     * @return <code>true</code> if this sheet displays gridlines.
     * @see #isPrintGridlines() to check if printing of gridlines is turned on or off
     */
    @Override
    public boolean isPrintGridlines() {
        return false;
    }

    /**
     * Sets the flag indicating whether this sheet should display the lines
     * between rows and columns to make editing and reading easier.
     * To turn printing of gridlines use {@link #setPrintGridlines(boolean)}
     *
     * @param show <code>true</code> if this sheet should display gridlines.
     * @see #setPrintGridlines(boolean)
     */
    @Override
    public void setPrintGridlines(boolean show) {

    }

    /**
     * Gets the print setup object.
     *
     * @return The user model for the print setup object.
     */
    @Override
    public PrintSetup getPrintSetup() {
        return null;
    }

    /**
     * Gets the user model for the default document header.
     * <p/>
     * Note that XSSF offers more kinds of document headers than HSSF does
     * </p>
     *
     * @return the document header. Never <code>null</code>
     */
    @Override
    public Header getHeader() {
        return null;
    }

    /**
     * Gets the user model for the default document footer.
     * <p/>
     * Note that XSSF offers more kinds of document footers than HSSF does.
     *
     * @return the document footer. Never <code>null</code>
     */
    @Override
    public Footer getFooter() {
        return null;
    }

    /**
     * Sets a flag indicating whether this sheet is selected.
     * <p>
     * Note: multiple sheets can be selected, but only one sheet can be active at one time.
     * </p>
     *
     * @param value <code>true</code> if this sheet is selected
     * @see org.apache.poi.ss.usermodel.Workbook#setActiveSheet(int)
     */
    @Override
    public void setSelected(boolean value) {

    }

    /**
     * Gets the size of the margin in inches.
     *
     * @param margin which margin to get
     * @return the size of the margin
     */
    @Override
    public double getMargin(short margin) {
        return 0;
    }

    /**
     * Sets the size of the margin in inches.
     *
     * @param margin which margin to get
     * @param size   the size of the margin
     */
    @Override
    public void setMargin(short margin, double size) {

    }

    /**
     * Answer whether protection is enabled or disabled
     *
     * @return true => protection enabled; false => protection disabled
     */
    @Override
    public boolean getProtect() {
        return false;
    }

    /**
     * Sets the protection enabled as well as the password
     *
     * @param password to set for protection. Pass <code>null</code> to remove protection
     */
    @Override
    public void protectSheet(String password) {

    }

    /**
     * Answer whether scenario protection is enabled or disabled
     *
     * @return true => protection enabled; false => protection disabled
     */
    @Override
    public boolean getScenarioProtect() {
        return false;
    }

    /**
     * Sets the zoom magnication for the sheet.  The zoom is expressed as a
     * fraction.  For example to express a zoom of 75% use 3 for the numerator
     * and 4 for the denominator.
     *
     * @param numerator   The numerator for the zoom magnification.
     * @param denominator The denominator for the zoom magnification.
     */
    @Override
    public void setZoom(int numerator, int denominator) {

    }

    /**
     * The top row in the visible view when the sheet is
     * first viewed after opening it in a viewer
     *
     * @return short indicating the rownum (0 based) of the top row
     */
    @Override
    public short getTopRow() {
        return 0;
    }

    /**
     * The left col in the visible view when the sheet is
     * first viewed after opening it in a viewer
     *
     * @return short indicating the rownum (0 based) of the top row
     */
    @Override
    public short getLeftCol() {
        return 0;
    }

    /**
     * Sets desktop window pane display area, when the
     * file is first opened in a viewer.
     *
     * @param toprow  the top row to show in desktop window pane
     * @param leftcol the left column to show in desktop window pane
     */
    @Override
    public void showInPane(int toprow, int leftcol) {

    }

    /**
     * Sets desktop window pane display area, when the
     * file is first opened in a viewer.
     *
     * @param toprow  the top row to show in desktop window pane
     * @param leftcol the left column to show in desktop window pane
     * @deprecated Use {@link #showInPane(int, int)} as there can be more than 32767 rows.
     */
    @Override
    public void showInPane(short toprow, short leftcol) {

    }

    /**
     * Shifts rows between startRow and endRow n number of rows.
     * If you use a negative number, it will shift rows up.
     * Code ensures that rows don't wrap around.
     * <p/>
     * Calls shiftRows(startRow, endRow, n, false, false);
     * <p/>
     * <p/>
     * Additionally shifts merged regions that are completely defined in these
     * rows (ie. merged 2 cells on a row to be shifted).
     *
     * @param startRow the row to start shifting
     * @param endRow   the row to end shifting
     * @param n        the number of rows to shift
     */
    @Override
    public void shiftRows(int startRow, int endRow, int n) {

    }

    /**
     * Shifts rows between startRow and endRow n number of rows.
     * If you use a negative number, it will shift rows up.
     * Code ensures that rows don't wrap around
     * <p/>
     * <p/>
     * Additionally shifts merged regions that are completely defined in these
     * rows (ie. merged 2 cells on a row to be shifted).
     * <p/>
     *
     * @param startRow               the row to start shifting
     * @param endRow                 the row to end shifting
     * @param n                      the number of rows to shift
     * @param copyRowHeight          whether to copy the row height during the shift
     * @param resetOriginalRowHeight whether to set the original row's height to the default
     */
    @Override
    public void shiftRows(int startRow, int endRow, int n, boolean copyRowHeight, boolean resetOriginalRowHeight) {

    }

    /**
     * Creates a split (freezepane). Any existing freezepane or split pane is overwritten.
     * <p>
     * If both colSplit and rowSplit are zero then the existing freeze pane is removed
     * </p>
     *
     * @param colSplit       Horizonatal position of split.
     * @param rowSplit       Vertical position of split.
     * @param leftmostColumn Left column visible in right pane.
     * @param topRow         Top row visible in bottom pane
     */
    @Override
    public void createFreezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {

    }

    /**
     * Creates a split (freezepane). Any existing freezepane or split pane is overwritten.
     * <p>
     * If both colSplit and rowSplit are zero then the existing freeze pane is removed
     * </p>
     *
     * @param colSplit Horizonatal position of split.
     * @param rowSplit Vertical position of split.
     */
    @Override
    public void createFreezePane(int colSplit, int rowSplit) {

    }

    /**
     * Creates a split pane. Any existing freezepane or split pane is overwritten.
     *
     * @param xSplitPos      Horizonatal position of split (in 1/20th of a point).
     * @param ySplitPos      Vertical position of split (in 1/20th of a point).
     * @param leftmostColumn Left column visible in right pane.
     * @param topRow         Top row visible in bottom pane
     * @param activePane     Active pane.  One of: PANE_LOWER_RIGHT,
     *                       PANE_UPPER_RIGHT, PANE_LOWER_LEFT, PANE_UPPER_LEFT
     * @see #PANE_LOWER_LEFT
     * @see #PANE_LOWER_RIGHT
     * @see #PANE_UPPER_LEFT
     * @see #PANE_UPPER_RIGHT
     */
    @Override
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, int activePane) {

    }

    /**
     * Returns the information regarding the currently configured pane (split or freeze)
     *
     * @return null if no pane configured, or the pane information.
     */
    @Override
    public PaneInformation getPaneInformation() {
        return null;
    }

    /**
     * Sets whether the gridlines are shown in a viewer
     *
     * @param show whether to show gridlines or not
     */
    @Override
    public void setDisplayGridlines(boolean show) {

    }

    /**
     * Returns if gridlines are displayed
     *
     * @return whether gridlines are displayed
     */
    @Override
    public boolean isDisplayGridlines() {
        return false;
    }

    /**
     * Sets whether the formulas are shown in a viewer
     *
     * @param show whether to show formulas or not
     */
    @Override
    public void setDisplayFormulas(boolean show) {

    }

    /**
     * Returns if formulas are displayed
     *
     * @return whether formulas are displayed
     */
    @Override
    public boolean isDisplayFormulas() {
        return false;
    }

    /**
     * Sets whether the RowColHeadings are shown in a viewer
     *
     * @param show whether to show RowColHeadings or not
     */
    @Override
    public void setDisplayRowColHeadings(boolean show) {

    }

    /**
     * Returns if RowColHeadings are displayed.
     *
     * @return whether RowColHeadings are displayed
     */
    @Override
    public boolean isDisplayRowColHeadings() {
        return false;
    }

    /**
     * Sets a page break at the indicated row
     * Breaks occur above the specified row and left of the specified column inclusive.
     * <p/>
     * For example, <code>sheet.setColumnBreak(2);</code> breaks the sheet into two parts
     * with columns A,B,C in the first and D,E,... in the second. Simuilar, <code>sheet.setRowBreak(2);</code>
     * breaks the sheet into two parts with first three rows (rownum=1...3) in the first part
     * and rows starting with rownum=4 in the second.
     *
     * @param row the row to break, inclusive
     */
    @Override
    public void setRowBreak(int row) {

    }

    /**
     * Determines if there is a page break at the indicated row
     *
     * @param row FIXME: Document this!
     * @return FIXME: Document this!
     */
    @Override
    public boolean isRowBroken(int row) {
        return false;
    }

    /**
     * Removes the page break at the indicated row
     *
     * @param row
     */
    @Override
    public void removeRowBreak(int row) {

    }

    /**
     * Retrieves all the horizontal page breaks
     *
     * @return all the horizontal page breaks, or null if there are no row page breaks
     */
    @Override
    public int[] getRowBreaks() {
        return new int[0];
    }

    /**
     * Retrieves all the vertical page breaks
     *
     * @return all the vertical page breaks, or null if there are no column page breaks
     */
    @Override
    public int[] getColumnBreaks() {
        return new int[0];
    }

    /**
     * Sets a page break at the indicated column.
     * Breaks occur above the specified row and left of the specified column inclusive.
     * <p/>
     * For example, <code>sheet.setColumnBreak(2);</code> breaks the sheet into two parts
     * with columns A,B,C in the first and D,E,... in the second. Simuilar, <code>sheet.setRowBreak(2);</code>
     * breaks the sheet into two parts with first three rows (rownum=1...3) in the first part
     * and rows starting with rownum=4 in the second.
     *
     * @param column the column to break, inclusive
     */
    @Override
    public void setColumnBreak(int column) {

    }

    /**
     * Determines if there is a page break at the indicated column
     *
     * @param column FIXME: Document this!
     * @return FIXME: Document this!
     */
    @Override
    public boolean isColumnBroken(int column) {
        return false;
    }

    /**
     * Removes a page break at the indicated column
     *
     * @param column
     */
    @Override
    public void removeColumnBreak(int column) {

    }

    /**
     * Expands or collapses a column group.
     *
     * @param columnNumber One of the columns in the group.
     * @param collapsed    true = collapse group, false = expand group.
     */
    @Override
    public void setColumnGroupCollapsed(int columnNumber, boolean collapsed) {

    }

    /**
     * Create an outline for the provided column range.
     *
     * @param fromColumn beginning of the column range.
     * @param toColumn   end of the column range.
     */
    @Override
    public void groupColumn(int fromColumn, int toColumn) {

    }

    /**
     * Ungroup a range of columns that were previously groupped
     *
     * @param fromColumn start column (0-based)
     * @param toColumn   end column (0-based)
     */
    @Override
    public void ungroupColumn(int fromColumn, int toColumn) {

    }

    /**
     * Tie a range of rows together so that they can be collapsed or expanded
     *
     * @param fromRow start row (0-based)
     * @param toRow   end row (0-based)
     */
    @Override
    public void groupRow(int fromRow, int toRow) {

    }

    /**
     * Ungroup a range of rows that were previously groupped
     *
     * @param fromRow start row (0-based)
     * @param toRow   end row (0-based)
     */
    @Override
    public void ungroupRow(int fromRow, int toRow) {

    }

    /**
     * Set view state of a groupped range of rows
     *
     * @param row      start row of a groupped range of rows (0-based)
     * @param collapse whether to expand/collapse the detail rows
     */
    @Override
    public void setRowGroupCollapsed(int row, boolean collapse) {

    }

    /**
     * Sets the default column style for a given column.  POI will only apply this style to new cells added to the sheet.
     *
     * @param column the column index
     * @param style  the style to set
     */
    @Override
    public void setDefaultColumnStyle(int column, CellStyle style) {

    }

    /**
     * Adjusts the column width to fit the contents.
     * <p/>
     * <p>
     * This process can be relatively slow on large sheets, so this should
     * normally only be called once per column, at the end of your
     * processing.
     * </p>
     * You can specify whether the content of merged cells should be considered or ignored.
     * Default is to ignore merged cells.
     *
     * @param column the column index
     */
    @Override
    public void autoSizeColumn(int column) {

    }

    /**
     * Adjusts the column width to fit the contents.
     * <p>
     * This process can be relatively slow on large sheets, so this should
     * normally only be called once per column, at the end of your
     * processing.
     * </p>
     * You can specify whether the content of merged cells should be considered or ignored.
     * Default is to ignore merged cells.
     *
     * @param column         the column index
     * @param useMergedCells whether to use the contents of merged cells when calculating the width of the column
     */
    @Override
    public void autoSizeColumn(int column, boolean useMergedCells) {

    }

    /**
     * Returns cell comment for the specified row and column
     *
     * @param row
     * @param column
     * @return cell comment or <code>null</code> if not found
     */
    @Override
    public Comment getCellComment(int row, int column) {
        return null;
    }

    /**
     * Creates the top-level drawing patriarch.
     * <p>This may then be used to add graphics or charts.</p>
     * <p>Note that this will normally have the effect of removing
     * any existing drawings on this sheet.</p>
     *
     * @return The new drawing patriarch.
     */
    @Override
    public Drawing createDrawingPatriarch() {
        return null;
    }

    /**
     * Return the parent workbook
     *
     * @return the parent workbook
     */
    @Override
    public Workbook getWorkbook() {
        return workbook;
    }

    /**
     * Returns the name of this sheet
     *
     * @return the name of this sheet
     */
    @Override
    public String getSheetName() {
        return sheetName;
    }

    /**
     * Note - this is not the same as whether the sheet is focused (isActive)
     *
     * @return <code>true</code> if this sheet is currently selected
     */
    @Override
    public boolean isSelected() {
        return false;
    }

    /**
     * Sets array formula to specified region for result.
     *
     * @param formula text representation of the formula
     * @param range   Region of array formula for result.
     * @return the {@link org.apache.poi.ss.usermodel.CellRange} of cells affected by this change
     */
    @Override
    public CellRange<? extends Cell> setArrayFormula(String formula, CellRangeAddress range) {
        return null;
    }

    /**
     * Remove a Array Formula from this sheet.  All cells contained in the Array Formula range are removed as well
     *
     * @param cell any cell within Array Formula range
     * @return the {@link org.apache.poi.ss.usermodel.CellRange} of cells affected by this change
     */
    @Override
    public CellRange<? extends Cell> removeArrayFormula(Cell cell) {
        return null;
    }

    @Override
    public DataValidationHelper getDataValidationHelper() {
        return null;
    }

    /**
     * Creates a data validation object
     *
     * @param dataValidation The Data validation object settings
     */
    @Override
    public void addValidationData(DataValidation dataValidation) {

    }

    /**
     * Enable filtering for a range of cells
     *
     * @param range the range of cells to filter
     */
    @Override
    public AutoFilter setAutoFilter(CellRangeAddress range) {
        return null;
    }

    /**
     * The 'Conditional Formatting' facet for this <tt>Sheet</tt>
     *
     * @return conditional formatting rule for this sheet
     */
    @Override
    public SheetConditionalFormatting getSheetConditionalFormatting() {
        return null;
    }

    /**
     * Gets the repeating rows used when printing the sheet, as found in
     * File->PageSetup->Sheet.
     * <p/>
     * Repeating rows cover a range of contiguous rows, e.g.:
     * <pre>
     * Sheet1!$1:$1
     * Sheet2!$5:$8
     * </pre>
     * The {@link org.apache.poi.ss.util.CellRangeAddress} returned contains a column part which spans
     * all columns, and a row part which specifies the contiguous range of
     * repeating rows.
     * <p/>
     * If the Sheet does not have any repeating rows defined, null is returned.
     *
     * @return an {@link org.apache.poi.ss.util.CellRangeAddress} containing the repeating rows for the
     * Sheet, or null.
     */
    @Override
    public CellRangeAddress getRepeatingRows() {
        return null;
    }

    /**
     * Gets the repeating columns used when printing the sheet, as found in
     * File->PageSetup->Sheet.
     * <p/>
     * Repeating columns cover a range of contiguous columns, e.g.:
     * <pre>
     * Sheet1!$A:$A
     * Sheet2!$C:$F
     * </pre>
     * The {@link org.apache.poi.ss.util.CellRangeAddress} returned contains a row part which spans all
     * rows, and a column part which specifies the contiguous range of
     * repeating columns.
     * <p/>
     * If the Sheet does not have any repeating columns defined, null is
     * returned.
     *
     * @return an {@link org.apache.poi.ss.util.CellRangeAddress} containing the repeating columns for
     * the Sheet, or null.
     */
    @Override
    public CellRangeAddress getRepeatingColumns() {
        return null;
    }

    /**
     * Sets the repeating rows used when printing the sheet, as found in
     * File->PageSetup->Sheet.
     * <p/>
     * Repeating rows cover a range of contiguous rows, e.g.:
     * <pre>
     * Sheet1!$1:$1
     * Sheet2!$5:$8</pre>
     * The parameter {@link org.apache.poi.ss.util.CellRangeAddress} should specify a column part
     * which spans all columns, and a row part which specifies the contiguous
     * range of repeating rows, e.g.:
     * <pre>
     * sheet.setRepeatingRows(CellRangeAddress.valueOf("2:3"));</pre>
     * A null parameter value indicates that repeating rows should be removed
     * from the Sheet:
     * <pre>
     * sheet.setRepeatingRows(null);</pre>
     *
     * @param rowRangeRef a {@link org.apache.poi.ss.util.CellRangeAddress} containing the repeating
     *                    rows for the Sheet, or null.
     */
    @Override
    public void setRepeatingRows(CellRangeAddress rowRangeRef) {

    }

    /**
     * Sets the repeating columns used when printing the sheet, as found in
     * File->PageSetup->Sheet.
     * <p/>
     * Repeating columns cover a range of contiguous columns, e.g.:
     * <pre>
     * Sheet1!$A:$A
     * Sheet2!$C:$F</pre>
     * The parameter {@link org.apache.poi.ss.util.CellRangeAddress} should specify a row part
     * which spans all rows, and a column part which specifies the contiguous
     * range of repeating columns, e.g.:
     * <pre>
     * sheet.setRepeatingColumns(CellRangeAddress.valueOf("B:C"));</pre>
     * A null parameter value indicates that repeating columns should be removed
     * from the Sheet:
     * <pre>
     * sheet.setRepeatingColumns(null);</pre>
     *
     * @param columnRangeRef a {@link org.apache.poi.ss.util.CellRangeAddress} containing the repeating
     *                       columns for the Sheet, or null.
     */
    @Override
    public void setRepeatingColumns(CellRangeAddress columnRangeRef) {

    }

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Row> iterator() {
        return rowIterator();
    }
}
