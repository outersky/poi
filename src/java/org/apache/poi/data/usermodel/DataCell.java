package org.apache.poi.data.usermodel;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Calendar;
import java.util.Date;

/**
 * DataCell.
 * contains only data related information, all render information is ignored.
 */
public class DataCell implements Cell {
    int columnIndex ;
    int rowIndex;
    Sheet sheet;
    DataRow row;
    int cellType;
    int cachedFormulaResultType;
    double doubleValue;
    String strValue;
    String formula;
    boolean boolValue;
    byte errorValue;
    CellStyle style;

    public DataCell(DataRow row){
        this.row = row;
        this.sheet = row.getSheet();
    }

    public void cloneFromCell(Cell cell){
        columnIndex = cell.getColumnIndex();
        rowIndex = cell.getRowIndex();
        cellType = cell.getCellType();
        style = new DataCellStyle();
        style.cloneStyleFrom(cell.getCellStyle());

        switch (cellType) {
            case CELL_TYPE_BLANK: break;
            case CELL_TYPE_BOOLEAN:
                boolValue = cell.getBooleanCellValue();
                break;
            case CELL_TYPE_STRING:
                strValue = cell.getStringCellValue();
                break;
            case CELL_TYPE_NUMERIC:
                doubleValue = cell.getNumericCellValue();
                break;
            case CELL_TYPE_ERROR:
                errorValue = cell.getErrorCellValue();
                break;
            case CELL_TYPE_FORMULA:
                formula = cell.getCellFormula();
                cachedFormulaResultType = cell.getCachedFormulaResultType();
                break;
            default:
                throw new IllegalStateException("Unexpected cell type (" + cellType + ")");
        }
    }

    /**
     * Returns column index of this cell
     *
     * @return zero-based column index of a column in a sheet.
     */
    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Returns row index of a row in the sheet that contains this cell
     *
     * @return zero-based row index of a row in the sheet that contains this cell
     */
    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Returns the sheet this cell belongs to
     *
     * @return the sheet this cell belongs to
     */
    @Override
    public Sheet getSheet() {
        return sheet;
    }

    /**
     * Returns the Row this cell belongs to
     *
     * @return the Row that owns this cell
     */
    @Override
    public Row getRow() {
        return row;
    }

    /**
     * Set the cells type (numeric, formula or string).
     * <p>If the cell currently contains a value, the value will
     * be converted to match the new type, if possible. Formatting
     * is generally lost in the process however.</p>
     * <p>If what you want to do is get a String value for your
     * numeric cell, <i>stop!</i>. This is not the way to do it.
     * Instead, for fetching the string value of a numeric or boolean
     * or date cell, use {@link DataFormatter} instead.</p>
     *
     * @param cellType
     * @throws IllegalArgumentException if the specified cell type is invalid
     * @throws IllegalStateException    if the current value cannot be converted to the new type
     * @see #CELL_TYPE_NUMERIC
     * @see #CELL_TYPE_STRING
     * @see #CELL_TYPE_FORMULA
     * @see #CELL_TYPE_BLANK
     * @see #CELL_TYPE_BOOLEAN
     * @see #CELL_TYPE_ERROR
     */
    @Override
    public void setCellType(int cellType) {
        this.cellType = cellType;
    }

    /**
     * Return the cell type.
     *
     * @return the cell type
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_BLANK
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_NUMERIC
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_STRING
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_FORMULA
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_BOOLEAN
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_ERROR
     */
    @Override
    public int getCellType() {
        return cellType;
    }

    /**
     * Only valid for formula cells
     *
     * @return one of ({@link #CELL_TYPE_NUMERIC}, {@link #CELL_TYPE_STRING},
     * {@link #CELL_TYPE_BOOLEAN}, {@link #CELL_TYPE_ERROR}) depending
     * on the cached value of the formula
     */
    @Override
    public int getCachedFormulaResultType() {
        return cachedFormulaResultType;
    }

    /**
     * Set a numeric value for the cell
     *
     * @param value the numeric value to set this cell to.  For formulas we'll set the
     *              precalculated value, for numerics we'll set its value. For other types we
     *              will change the cell to a numeric cell and set its value.
     */
    @Override
    public void setCellValue(double value) {
        doubleValue = value;
    }

    /**
     * Converts the supplied date to its equivalent Excel numeric value and sets
     * that into the cell.
     * <p/>
     * <b>Note</b> - There is actually no 'DATE' cell type in Excel. In many
     * cases (when entering date values), Excel automatically adjusts the
     * <i>cell style</i> to some date format, creating the illusion that the cell
     * data type is now something besides {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_NUMERIC}.  POI
     * does not attempt to replicate this behaviour.  To make a numeric cell
     * display as a date, use {@link #setCellStyle(CellStyle)} etc.
     *
     * @param value the numeric value to set this cell to.  For formulas we'll set the
     *              precalculated value, for numerics we'll set its value. For other types we
     *              will change the cell to a numerics cell and set its value.
     */
    @Override
    public void setCellValue(Date value) {
        doubleValue = DateUtil.getExcelDate(value);
    }

    /**
     * Set a date value for the cell. Excel treats dates as numeric so you will need to format the cell as
     * a date.
     * <p>
     * This will set the cell value based on the Calendar's timezone. As Excel
     * does not support timezones this means that both 20:00+03:00 and
     * 20:00-03:00 will be reported as the same value (20:00) even that there
     * are 6 hours difference between the two times. This difference can be
     * preserved by using <code>setCellValue(value.getTime())</code> which will
     * automatically shift the times to the default timezone.
     * </p>
     *
     * @param value the date value to set this cell to.  For formulas we'll set the
     *              precalculated value, for numerics we'll set its value. For othertypes we
     *              will change the cell to a numeric cell and set its value.
     */
    @Override
    public void setCellValue(Calendar value) {
        setCellValue(value.getTime());
    }

    /**
     * Set a rich string value for the cell.
     *
     * @param value value to set the cell to.  For formulas we'll set the formula
     *              string, for String cells we'll set its value.  For other types we will
     *              change the cell to a string cell and set its value.
     *              If value is null then we will change the cell to a Blank cell.
     */
    @Override
    public void setCellValue(RichTextString value) {
        strValue = value.getString();
    }

    /**
     * Set a string value for the cell.
     *
     * @param value value to set the cell to.  For formulas we'll set the formula
     *              string, for String cells we'll set its value.  For other types we will
     *              change the cell to a string cell and set its value.
     *              If value is null then we will change the cell to a Blank cell.
     */
    @Override
    public void setCellValue(String value) {
        strValue = value;
    }

    /**
     * Sets formula for this cell.
     * <p>
     * Note, this method only sets the formula string and does not calculate the formula value.
     * To set the precalculated value use {@link #setCellValue(double)} or {@link #setCellValue(String)}
     * </p>
     *
     * @param formula the formula to set, e.g. <code>"SUM(C4:E4)"</code>.
     *                If the argument is <code>null</code> then the current formula is removed.
     * @throws org.apache.poi.ss.formula.FormulaParseException if the formula has incorrect syntax or is otherwise invalid
     */
    @Override
    public void setCellFormula(String formula) throws FormulaParseException {
        this.formula = formula;
    }

    /**
     * Return a formula for the cell, for example, <code>SUM(C4:E4)</code>
     *
     * @return a formula for the cell
     * @throws IllegalStateException if the cell type returned by {@link #getCellType()} is not CELL_TYPE_FORMULA
     */
    @Override
    public String getCellFormula() {
        return formula;
    }

    /**
     * Get the value of the cell as a number.
     * <p>
     * For strings we throw an exception. For blank cells we return a 0.
     * For formulas or error cells we return the precalculated value;
     * </p>
     *
     * @return the value of the cell as a number
     * @throws IllegalStateException if the cell type returned by {@link #getCellType()} is CELL_TYPE_STRING
     * @throws NumberFormatException if the cell value isn't a parsable <code>double</code>.
     * @see DataFormatter for turning this number into a string similar to that which Excel would render this number as.
     */
    @Override
    public double getNumericCellValue() {
        return doubleValue;
    }

    /**
     * Get the value of the cell as a date.
     * <p>
     * For strings we throw an exception. For blank cells we return a null.
     * </p>
     *
     * @return the value of the cell as a date
     * @throws IllegalStateException if the cell type returned by {@link #getCellType()} is CELL_TYPE_STRING
     * @throws NumberFormatException if the cell value isn't a parsable <code>double</code>.
     * @see DataFormatter for formatting  this date into a string similar to how excel does.
     */
    @Override
    public Date getDateCellValue() {
        return DateUtil.getJavaDate(doubleValue);
    }

    /**
     * Get the value of the cell as a XSSFRichTextString
     * <p>
     * For numeric cells we throw an exception. For blank cells we return an empty string.
     * For formula cells we return the pre-calculated value if a string, otherwise an exception.
     * </p>
     *
     * @return the value of the cell as a XSSFRichTextString
     */
    @Override
    public RichTextString getRichStringCellValue() {
        return null;
    }

    /**
     * Get the value of the cell as a string
     * <p>
     * For numeric cells we throw an exception. For blank cells we return an empty string.
     * For formulaCells that are not string Formulas, we throw an exception.
     * </p>
     *
     * @return the value of the cell as a string
     */
    @Override
    public String getStringCellValue() {
        return strValue;
    }

    /**
     * Set a boolean value for the cell
     *
     * @param value the boolean value to set this cell to.  For formulas we'll set the
     *              precalculated value, for booleans we'll set its value. For other types we
     *              will change the cell to a boolean cell and set its value.
     */
    @Override
    public void setCellValue(boolean value) {
        boolValue = value;
    }

    /**
     * Set a error value for the cell
     *
     * @param value the error value to set this cell to.  For formulas we'll set the
     *              precalculated value , for errors we'll set
     *              its value. For other types we will change the cell to an error
     *              cell and set its value.
     * @see FormulaError
     */
    @Override
    public void setCellErrorValue(byte value) {
        errorValue = value;
    }

    /**
     * Get the value of the cell as a boolean.
     * <p>
     * For strings, numbers, and errors, we throw an exception. For blank cells we return a false.
     * </p>
     *
     * @return the value of the cell as a boolean
     * @throws IllegalStateException if the cell type returned by {@link #getCellType()}
     *                               is not CELL_TYPE_BOOLEAN, CELL_TYPE_BLANK or CELL_TYPE_FORMULA
     */
    @Override
    public boolean getBooleanCellValue() {
        return boolValue;
    }

    /**
     * Get the value of the cell as an error code.
     * <p>
     * For strings, numbers, and booleans, we throw an exception.
     * For blank cells we return a 0.
     * </p>
     *
     * @return the value of the cell as an error code
     * @throws IllegalStateException if the cell type returned by {@link #getCellType()} isn't CELL_TYPE_ERROR
     * @see FormulaError for error codes
     */
    @Override
    public byte getErrorCellValue() {
        return errorValue;
    }

    /**
     * Set the style for the cell.  The style should be an CellStyle created/retreived from
     * the Workbook.
     *
     * @param style reference contained in the workbook.
     *              If the value is null then the style information is removed causing the cell to used the default workbook style.
     * @see org.apache.poi.ss.usermodel.Workbook#createCellStyle()
     */
    @Override
    public void setCellStyle(CellStyle style) {
        this.style = style;
    }

    /**
     * Return the cell's style.
     *
     * @return the cell's style. Always not-null. Default cell style has zero index and can be obtained as
     * <code>workbook.getCellStyleAt(0)</code>
     * @see org.apache.poi.ss.usermodel.Workbook#getCellStyleAt(short)
     */
    @Override
    public CellStyle getCellStyle() {
        return style;
    }

    /**
     * Sets this cell as the active cell for the worksheet
     */
    @Override
    public void setAsActiveCell() {

    }

    /**
     * Assign a comment to this cell
     *
     * @param comment comment associated with this cell
     */
    @Override
    public void setCellComment(Comment comment) {

    }

    /**
     * Returns comment associated with this cell
     *
     * @return comment associated with this cell or <code>null</code> if not found
     */
    @Override
    public Comment getCellComment() {
        return null;
    }

    /**
     * Removes the comment for this cell, if there is one.
     */
    @Override
    public void removeCellComment() {

    }

    /**
     * @return hyperlink associated with this cell or <code>null</code> if not found
     */
    @Override
    public Hyperlink getHyperlink() {
        return null;
    }

    /**
     * Assign a hyperlink to this cell
     *
     * @param link hyperlink associated with this cell
     */
    @Override
    public void setHyperlink(Hyperlink link) {

    }

    /**
     * Only valid for array formula cells
     *
     * @return range of the array formula group that the cell belongs to.
     */
    @Override
    public CellRangeAddress getArrayFormulaRange() {
        return null;
    }

    /**
     * @return <code>true</code> if this cell is part of group of cells having a common array formula.
     */
    @Override
    public boolean isPartOfArrayFormulaGroup() {
        return false;
    }
}
