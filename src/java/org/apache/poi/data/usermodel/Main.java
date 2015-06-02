package org.apache.poi.data.usermodel;

import java.io.FileInputStream;
import org.apache.poi.data.usermodel.formula.Evaluator;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 */
public class Main {
    
    static HSSFDataFormatter df = new HSSFDataFormatter();

    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();
        for(int i=0; i<1; i++){
            testPoi();
        }
        System.out.println("Time " + ( System.currentTimeMillis()-time) + " (ms)" );
//        System.out.println("Countifs Time " + ( Countifs.times ) + " (ms)" );
    }
    
    private static void testPoi() throws Exception {
        Workbook wb = WorkbookFactory.create(new FileInputStream("/tmp/sw.xls"));
        
        Evaluator.registerAll(wb);
        HSSFSheet sheet1 = (HSSFSheet) wb.getSheet("项目清单（宏站）");
        HSSFSheet sheet2 = (HSSFSheet) wb.getSheet("项目清单（室分）");
        sheet1.freeze();
        sheet2.freeze();

        evaluate(wb);
        
    }
    
    private static void evaluate(Workbook wb){
//        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        FormulaEvaluator evaluator = new HSSFFormulaEvaluator((HSSFWorkbook)wb,IStabilityClassifier.TOTALLY_IMMUTABLE);
        HSSFSheet sheet1 = (HSSFSheet) wb.getSheet("Huizong2");
        for(Row row : sheet1){
            for(Cell cell : row){
//                System.out.println("" + cell.getRowIndex() + "-" + cell.getColumnIndex()  );
                if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    Cell c = evaluator.evaluateInCell(cell);
//                    debug(c,df.formatCellValue(c));
//                    switch(c.getCellType()){
//                        case Cell.CELL_TYPE_NUMERIC: debug(c,c.getNumericCellValue());break;
//                        case Cell.CELL_TYPE_STRING: debug(c,c.getStringCellValue());break;
//                        case Cell.CELL_TYPE_BOOLEAN: debug(c,c.getBooleanCellValue());break;
//                    }
                    
                }
                debug(cell,df.formatCellValue(cell));
            }
            System.out.println("");
        }

    }
    
    private static void debug(Cell c, Object obj){
        System.out.print(" [" + c.getRowIndex() + "-" + c.getColumnIndex() + " : "  + obj.toString() + "] ");
    }
}
