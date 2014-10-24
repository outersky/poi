package org.apache.poi.data.usermodel;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

/**
 *
 */
public class DataRow implements Row{

    DataSheet sheet ;
    int rowNum;
    DataCell[] cells;

    public DataRow(DataSheet sheet){
        this.sheet = sheet;
    }

    public void cloneFromRow(Row source){
        int size = source.getLastCellNum()+1;
        cells = new DataCell[size];
        for(Cell cell : source){
            DataCell dataCell = new DataCell(this);
            dataCell.cloneFromCell(cell);
            cells[cell.getColumnIndex()] = dataCell;
        }
    }
    /**
     * Use this to create new cells within the row and return it.
     * <p/>
     * The cell that is returned is a {@link org.apache.poi.ss.usermodel.Cell#CELL_TYPE_BLANK}. The type can be changed
     * either through calling <code>setCellValue</code> or <code>setCellType</code>.
     *
     * @param column - the column number this cell represents
     * @return Cell a high level representation of the created cell.
     * @throws IllegalArgumentException if columnIndex < 0 or greater than the maximum number of supported columns
     *                                  (255 for *.xls, 1048576 for *.xlsx)
     */
    @Override
    public Cell createCell(int column) {
        return null;
    }

    /**
     * Use this to create new cells within the row and return it.
     * <p/>
     * The cell that is returned will be of the requested type.
     * The type can be changed either through calling setCellValue
     * or setCellType, but there is a small overhead to doing this,
     * so it is best to create of the required type up front.
     *
     * @param column - the column number this cell represents
     * @param type   - the cell's data type
     * @return Cell a high level representation of the created cell.
     * @throws IllegalArgumentException if columnIndex < 0 or greate than a maximum number of supported columns
     *                                  (255 for *.xls, 1048576 for *.xlsx)
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_BLANK
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_BOOLEAN
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_ERROR
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_FORMULA
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_NUMERIC
     * @see org.apache.poi.ss.usermodel.Cell#CELL_TYPE_STRING
     */
    @Override
    public Cell createCell(int column, int type) {
        return null;
    }

    /**
     * Remove the Cell from this row.
     *
     * @param cell the cell to remove
     */
    @Override
    public void removeCell(Cell cell) {

    }

    /**
     * Set the row number of this row.
     *
     * @param rowNum the row number (0-based)
     * @throws IllegalArgumentException if rowNum < 0
     */
    @Override
    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    /**
     * Get row number this row represents
     *
     * @return the row number (0 based)
     */
    @Override
    public int getRowNum() {
        return rowNum;
    }

    /**
     * Get the cell representing a given column (logical cell) 0-based.  If you
     * ask for a cell that is not defined....you get a null.
     *
     * @param cellnum 0 based column number
     * @return Cell representing that column or null if undefined.
     * @see #getCell(int, org.apache.poi.ss.usermodel.Row.MissingCellPolicy)
     */
    @Override
    public Cell getCell(int cellnum) {
        if(cellnum<0 || cellnum>=cells.length){
            return null;
        }
        return cells[cellnum];
    }

    /**
     * Returns the cell at the given (0 based) index, with the specified {@link org.apache.poi.ss.usermodel.Row.MissingCellPolicy}
     *
     * @param cellnum
     * @param policy
     * @return the cell at the given (0 based) index
     * @throws IllegalArgumentException if cellnum < 0 or the specified MissingCellPolicy is invalid
     * @see org.apache.poi.ss.usermodel.Row#RETURN_NULL_AND_BLANK
     * @see org.apache.poi.ss.usermodel.Row#RETURN_BLANK_AS_NULL
     * @see org.apache.poi.ss.usermodel.Row#CREATE_NULL_AS_BLANK
     */
    @Override
    public Cell getCell(int cellnum, MissingCellPolicy policy) {
        return getCell(cellnum);
    }

    /**
     * Get the number of the first cell contained in this row.
     *
     * @return short representing the first logical cell in the row,
     * or -1 if the row does not contain any cells.
     */
    @Override
    public short getFirstCellNum() {
        if(cells!=null && cells.length>0){
            return (short) cells[0].getColumnIndex();
        }
        return -1;
    }

    /**
     * Gets the index of the last cell contained in this row <b>PLUS ONE</b>. The result also
     * happens to be the 1-based column number of the last cell.  This value can be used as a
     * standard upper bound when iterating over cells:
     * <pre>
     * short minColIx = row.getFirstCellNum();
     * short maxColIx = row.getLastCellNum();
     * for(short colIx=minColIx; colIx&lt;maxColIx; colIx++) {
     *   Cell cell = row.getCell(colIx);
     *   if(cell == null) {
     *     continue;
     *   }
     *   //... do something with cell
     * }
     * </pre>
     *
     * @return short representing the last logical cell in the row <b>PLUS ONE</b>,
     * or -1 if the row does not contain any cells.
     */
    @Override
    public short getLastCellNum() {
        if(cells!=null && cells.length>0){
            return (short) cells[cells.length-1].getColumnIndex();
        }
        return -1;
    }

    /**
     * Gets the number of defined cells (NOT number of cells in the actual row!).
     * That is to say if only columns 0,4,5 have values then there would be 3.
     *
     * @return int representing the number of defined cells in the row.
     */
    @Override
    public int getPhysicalNumberOfCells() {
        return 0;
    }

    /**
     * Set the row's height or set to ff (-1) for undefined/default-height.  Set the height in "twips" or
     * 1/20th of a point.
     *
     * @param height rowheight or 0xff for undefined (use sheet default)
     */
    @Override
    public void setHeight(short height) {

    }

    /**
     * Set whether or not to display this row with 0 height
     *
     * @param zHeight height is zero or not.
     */
    @Override
    public void setZeroHeight(boolean zHeight) {

    }

    /**
     * Get whether or not to display this row with 0 height
     *
     * @return - zHeight height is zero or not.
     */
    @Override
    public boolean getZeroHeight() {
        return false;
    }

    /**
     * Set the row's height in points.
     *
     * @param height the height in points. <code>-1</code>  resets to the default height
     */
    @Override
    public void setHeightInPoints(float height) {

    }

    /**
     * Get the row's height measured in twips (1/20th of a point). If the height is not set, the default worksheet value is returned,
     * See {@link Sheet#getDefaultRowHeightInPoints()}
     *
     * @return row height measured in twips (1/20th of a point)
     */
    @Override
    public short getHeight() {
        return 0;
    }

    /**
     * Returns row height measured in point size. If the height is not set, the default worksheet value is returned,
     * See {@link Sheet#getDefaultRowHeightInPoints()}
     *
     * @return row height measured in point size
     * @see Sheet#getDefaultRowHeightInPoints()
     */
    @Override
    public float getHeightInPoints() {
        return 0;
    }

    /**
     * Is this row formatted? Most aren't, but some rows
     * do have whole-row styles. For those that do, you
     * can get the formatting from {@link #getRowStyle()}
     */
    @Override
    public boolean isFormatted() {
        return false;
    }

    /**
     * Returns the whole-row cell styles. Most rows won't
     * have one of these, so will return null. Call
     * {@link #isFormatted()} to check first.
     */
    @Override
    public CellStyle getRowStyle() {
        return null;
    }

    /**
     * Applies a whole-row cell styling to the row.
     *
     * @param style
     */
    @Override
    public void setRowStyle(CellStyle style) {

    }

    /**
     * @return Cell iterator of the physically defined cells.  Note element 4 may
     * actually be row cell depending on how many are defined!
     */
    @Override
    public Iterator<Cell> cellIterator() {
        return new ArrayIterator(cells);
    }

    /**
     * Returns the Sheet this row belongs to
     *
     * @return the Sheet that owns this row
     */
    @Override
    public Sheet getSheet() {
        return sheet;
    }

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Cell> iterator() {
        return cellIterator();
    }
}
