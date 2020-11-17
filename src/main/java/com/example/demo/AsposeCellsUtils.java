package com.example.demo;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.aspose.words.*;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataTable;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.aspectj.weaver.ast.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AsposeCellsUtils {
    /* 转PDF格式值 */
    private static final int wdFormatPDF = 17;

    /**
     * 获取license
     *
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream(".\\src\\main\\resource\\license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 支持DOC, DOCX, OOXML, RTF, HTML, OpenDocument, PDF, EPUB, XPS, SWF等相互转换<br>
     * @param inFileSrc 原始路径
     * @param outFileSrc  输出路径
     */
    public static void fileSaveToPDF(String inFileSrc,String outFileSrc){
        // 验证License
        if (!getLicense()) {
            return;
        }
        try {
            long old = System.currentTimeMillis();
            Workbook wb = new Workbook(inFileSrc);// 原始excel路径
            File pdfFile = new File(outFileSrc);// 输出路径
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            wb.save(fileOS, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            System.out.println("excel转pdf共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static String uploadDoc(HttpServletRequest request, String[] Flds, Object[] Vals,List<Map<String,Object>> docList){
//        HttpServletResponse response = getResponse();
//        String destdoc="";
//        // 验证License
//        if (!getLicense()) {
//            return destdoc;
//        }
//        try {
//            //模板word
//            String template = request.getSession().getServletContext().getRealPath("excelMould/dajdsqbg.docx");
//            //定义文档接口
//            Document doc = new Document(template);
//            DocumentBuilder builder = new DocumentBuilder(doc);
//            DataTable staffTable = new DataTable("list");
//            staffTable.getColumns().add("number");
//            staffTable.getColumns().add("archiveNo");
//            staffTable.getColumns().add("name");
//            staffTable.getColumns().add("view");
//            staffTable.getColumns().add("rmark");
//            if(docList!=null&&docList.size()>0){
//                for(int i=0;i<docList.size();i++){
//                    Map<String, Object> map=docList.get(i);
//                    DataRow row=staffTable.newRow();
//                    row.set(0,map.get("number"));
//                    row.set(1,map.get("archiveNo"));
//                    row.set(2,map.get("name"));
//                    row.set(3,map.get("view"));
//                    row.set(4,map.get("rmark"));
//                    staffTable.getRows().add(row);
//                }
//            }
//            //调用接口
//            doc.getMailMerge().executeWithRegions(staffTable);
//            doc.getMailMerge().execute(Flds, Vals);
//            //第四步 保存新word文档
//            String dest = "档案鉴定申请报告.docx"; //可以是doc或docx
//            response.reset();
//            response.setContentType("application/x-msdownload");
//            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("档案鉴定申请报告.doc", "UTF-8"));//设置编码格式
//            OutputStream out = response.getOutputStream();
//            doc.save(dest);
//            doc.save(out, SaveOptions.createSaveOptions(com.aspose.words.SaveFormat.DOC));
//            out.close();
//            System.out.println("完成");
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return destdoc;
//    }

    /**
     * 将图片转换成pdf文件
     *imgFilePath 需要被转换的img所存放的位置。 例如imgFilePath="D:\\projectPath\\55555.jpg";
     *pdfFilePath 转换后的pdf所存放的位置 例如pdfFilePath="D:\\projectPath\\test.pdf";
     * @throws IOException
     */
    public static boolean imgToPdf(String imgFilePath, String pdfFilePath)throws IOException {
        File file=new File(imgFilePath);
        if(file.exists()){
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(pdfFilePath);
                PdfWriter.getInstance(document, fos);

                // 添加PDF文档的某些信息，比如作者，主题等等
                //document.addAuthor("arui");
                //document.addSubject("test pdf.");
                // 设置文档的大小
                document.setPageSize(PageSize.A4);
                // 打开文档
                document.open();
                // 写入一段文字
                //document.add(new Paragraph("JUST TEST ..."));
                // 读取一个图片
                Image image = Image.getInstance(imgFilePath);
                float imageHeight=image.getScaledHeight();
                float imageWidth=image.getScaledWidth();
                int i=0;
                while(imageHeight>500||imageWidth>500){
                    image.scalePercent(100-i);
                    i++;
                    imageHeight=image.getScaledHeight();
                    imageWidth=image.getScaledWidth();
                }

                image.setAlignment(Image.ALIGN_CENTER);
                //     //设置图片的绝对位置
                // image.setAbsolutePosition(0, 0);
                // image.scaleAbsolute(500, 400);
                // 插入一个图片
                document.add(image);
            } catch (DocumentException de) {
                System.out.println(de.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            document.close();
            fos.flush();
            fos.close();
            return true;
        }else{
            return false;
        }
    }

    /**
     * Word文档转换
     *
     * @param inputFile
     * @param pdfFile
     */
    public static boolean word2PDF(String inputFile, String pdfFile) {
        ComThread.InitMTA(true);
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");// 创建一个word对象
            app.setProperty("Visible", new Variant(false)); // 不可见打开word
            app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch docs = app.getProperty("Documents").toDispatch();// 获取文挡属性

            System.out.println("打开文档 >>> " + inputFile);
            // Object[]第三个参数是表示“是否只读方式打开”
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();
            System.out.println("转换文档 [" + inputFile + "] >>> [" + pdfFile + "]");
            // 调用Document对象的SaveAs方法，将文档保存为pdf格式
            // word保存为pdf格式宏，值为17
            Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17

            long end = System.currentTimeMillis();

            System.out.println("用时：" + (end - start) + "ms.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            Dispatch.call(doc, "Close", false);
            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[] {});
            // 如果没有这句话,winword.exe进程将不会关闭
            ComThread.Release();
            ComThread.quitMainSTA();
        }
        return false;
    }



    public static void main(String[] args) throws IOException {
        //AsposeCellsUtils.fileSaveToPDF("C:\\Users\\邓牡丹\\Downloads\\归档文件.xls","C:\\Users\\邓牡丹\\Downloads\\归档文件.pdf");
        //AsposeCellsUtils.uploadDoc();
        //AsposeCellsUtils.uploadDocDajdb();
        boolean res = AsposeCellsUtils.word2PDF("D:\\ssss.docx", "D:\\ssss.pdf");

//        AsposeCellsUtils.word2PDF("D:\\ssss.docx","D:\\ssss.pdf");

    }

}
