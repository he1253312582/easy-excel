package com.heq.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.heq.comp.handler.CustomCellWriteHandler;
import com.heq.comp.handler.CustomSheetWriteHandler;
import com.heq.entity.excel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.junit.Test;

import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelWriteDomeService {

    private static final String fileName = "D:\\ideaWorkSpaces\\easyexcel\\repository\\excel\\write\\" + System.currentTimeMillis() + ".xlsx";


    /**
     * 最简单的写法
     * 创建数据模板,设置文件路径即可
     */
    @Test
    public void simpleWrite1() {
        //写法一:
        ExcelWriterBuilder writerBuilder = EasyExcel.write(fileName, ExcelDemoEntity.class);
        writerBuilder.sheet(0, "日期数据").doWrite(getDemoEntityData());
    }

    /**
     * 简单写法二:
     */
    @Test
    public void simpleWrite2() {
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelDemoEntity.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板信息").sheetNo(0).build();
        excelWriter.write(getDemoEntityData(), writeSheet);
        excelWriter.finish();
    }


    /**
     * 指定索要写入的列
     * 默认写入到sheet0
     * 1. 创建excel对应的实体对象 参照{@link IndexData}
     * 2. 使用{@link ExcelProperty}注解指定写入的列
     * 3. 直接写即可
     */
    @Test
    public void test3() {
        ExcelWriter excelWriter = EasyExcel.write(fileName, IndexData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板信息").build();
        excelWriter.write(getIndexData(), writeSheet);
        excelWriter.finish();
    }

    /**
     * 复杂头写入
     * 1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * 2. 使用{@link ExcelProperty}注解指定复杂的头
     * 3. 直接写即可
     */
    @Test
    public void test4() {
        EasyExcel.write(fileName, ComplexHeadData.class).sheet(0, "模板信息").doWrite(getComplexHeadData());
    }

    /**
     * 重复多次写入同一个sheet
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * <p>
     * 2. 使用{@link ExcelProperty}注解指定复杂的头
     * <p>
     * 3. 直接调用二次写入即可
     */
    @Test
    public void repeatedWrite1() {
        // 这里 需要指定写用哪个class去读
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelDemoEntity.class).build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < 5; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(getDemoEntityData(), writeSheet);
        }
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 重复多次写入不同的sheet中
     * 1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * 2. 使用{@link ExcelProperty}注解指定复杂的头
     * 3. 直接调用二次写入即可
     */
    @Test
    public void repeatedWrite2() {
        // 这里 指定文件
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelDemoEntity.class).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        WriteSheet writeSheet;
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo
            writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(getDemoEntityData(), writeSheet);
        }
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 重复多次写入不同的sheet中
     * 1. 创建excel对应的实体对象 参照{@link ComplexHeadData}
     * 2. 使用{@link ExcelProperty}注解指定复杂的头
     * 3. 直接调用二次写入即可
     */
    @Test
    public void repeatedWrite3() {
        // 方法3 如果写到不同的sheet 不同的对象
        // 这里 指定文件
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
        WriteSheet writeSheet;
        for (int i = 0; i < 5; i++) {
            // 每次都要创建writeSheet 这里注意必须指定sheetNo。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
            writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(ExcelDemoEntity.class).build();
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
            excelWriter.write(getDemoEntityData(), writeSheet);
        }
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }


    /**
     * 日期、数字或者自定义格式转换
     * 1. 创建excel对应的实体对象 参照{@link ConverterData}
     * 2. 使用{@link ExcelProperty}配合使用注解{@link DateTimeFormat}、{@link NumberFormat}或者自定义注解
     * 3. 直接写即可
     */
    @Test
    public void converterWrite() {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ConverterData.class).sheet("模板").doWrite(getConverterData());
    }

    /**
     * 图片导出
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ImageData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void imageWrite() throws Exception {
        // 如果使用流 记得关闭
        InputStream inputStream = null;
        try {
            List<ImageData> list = new ArrayList<ImageData>();
            ImageData imageData = new ImageData();
            list.add(imageData);
            String imagePath = this.getClass().getResource("/").getPath() + "images" + File.separator + "img.jpg";
            // 放入四种类型的图片 实际使用只要选一种即可
            imageData.setByteArray(FileUtils.readFileToByteArray(new File(imagePath)));
            imageData.setFile(new File(imagePath));
            imageData.setString(imagePath);
            inputStream = FileUtils.openInputStream(new File(imagePath));
            imageData.setInputStream(inputStream);
            EasyExcel.write(fileName, ImageData.class).sheet().doWrite(list);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 根据模板写入
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link IndexData}
     * <p>
     * 2. 使用{@link ExcelProperty}注解指定写入的列
     * <p>
     * 3. 使用withTemplate 读取模板
     * <p>
     * 4. 直接写即可
     */
    @Test
    public void templateWrite() {
        String templateFileName = "D:\\ideaWorkSpaces\\easyexcel\\src\\test\\resources\\templte\\demo.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ExcelDemoEntity.class).withTemplate(templateFileName).sheet(0).needHead(false).doWrite(getDemoEntityData());
    }


    /**
     * 列宽、行高
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link WidthAndHeightData}
     * <p>
     * 2. 使用注解{@link ColumnWidth}、{@link HeadRowHeight}、{@link ContentRowHeight}指定宽度或高度
     * <p>
     * 3. 直接写即可
     */
    @Test
    public void widthAndHeightWrite() {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, WidthAndHeightData.class).sheet("模板").doWrite(getWidthAndHeightData());

    }

    /**
     * 自定义样式
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelDemoEntity}
     * <p>
     * 2. 创建一个style策略 并注册
     * <p>
     * 3. 直接写即可
     */
    @Test
    public void styleWrite() {
        //设置标题策略
        WriteCellStyle headCellStyle = new WriteCellStyle();
        headCellStyle.setFillForegroundColor(IndexedColors.RED.index);
        //设置字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 20);
        headCellStyle.setWriteFont(headWriteFont);

        // 内容的风格
        WriteCellStyle contentCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short) 20);
        contentCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy=new HorizontalCellStyleStrategy(headCellStyle,contentCellStyle);
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ExcelDemoEntity.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板").doWrite(getDemoEntityData());
    }


    /**
     * 合并单元格
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelDemoEntity}
     * <p>
     * 2. 创建一个merge策略 并注册
     * <p>
     * 3. 直接写即可
     */
    @Test
    public void mergeWrite() {
        // 每隔2行会合并 把eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ExcelDemoEntity.class).registerWriteHandler(loopMergeStrategy).sheet("模板").doWrite(getDemoEntityData());
    }


    /**
     * 使用table去写入
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelDemoEntity}
     * <p>
     * 2. 然后写入table即可
     */
    @Test
    public void tableWrite() {
        // 这里直接写多个table的案例了，如果只有一个 也可以直一行代码搞定，参照其他案例
        // 这里 需要指定写用哪个class去读
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelDemoEntity.class).build();
        // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
        // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
        WriteTable writeTable0 = EasyExcel.writerTable(0).needHead(Boolean.TRUE).build();
        WriteTable writeTable1 = EasyExcel.writerTable(1).needHead(Boolean.TRUE).build();
        // 第一次写入会创建头
        excelWriter.write(getDemoEntityData(), writeSheet, writeTable0);
        // 第二次写如也会创建头，然后在第一次的后面写入数据
        excelWriter.write(getDemoEntityData(), writeSheet, writeTable1);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 动态头，实时生成头写入
     * <p>
     * 思路是这样子的，先创建List<String>头格式的sheet仅仅写入头,然后通过table 不写入头的方式 去写入数据
     *
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelDemoEntity}
     * <p>
     * 2. 然后写入table即可
     */
    @Test
    public void dynamicHeadWrite() {
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head()).sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(getDemoEntityData());
    }

    /**
     * 自动列宽(不太精确)
     * <p>
     * 这个目前不是很好用，比如有数字就会导致换行。而且长度也不是刚好和实际长度一致。 所以需要精确到刚好列宽的慎用。 当然也可以自己参照
     * {@link LongestMatchColumnWidthStyleStrategy}重新实现.
     * <p>
     * poi 自带{@link SXSSFSheet#autoSizeColumn(int)} 对中文支持也不太好。目前没找到很好的算法。 有的话可以推荐下。
     *
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link LongestMatchColumnWidthData}
     * <p>
     * 2. 注册策略{@link LongestMatchColumnWidthStyleStrategy}
     * <p>
     * 3. 直接写即可
     */
    @Test
    public void longestMatchColumnWidthWrite() {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, LongestMatchColumnWidthData.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(getLongestMatchColumnWidthData());
    }

    /**
     * 下拉，超链接等自定义拦截器（上面几点都不符合但是要对单元格进行操作的参照这个）
     * <p>
     * demo这里实现2点。1. 对第一行第一列的头超链接到:https://github.com/alibaba/easyexcel 2. 对第一列第一行和第二行的数据新增下拉框，显示 测试1 测试2
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelDemoEntity}
     * <p>
     * 2. 注册拦截器 {@link CustomCellWriteHandler} {@link CustomSheetWriteHandler}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void customHandlerWrite() {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, ExcelDemoEntity.class).registerWriteHandler(new CustomSheetWriteHandler())
                .registerWriteHandler(new CustomCellWriteHandler()).sheet("模板").doWrite(getDemoEntityData());
    }

    /**
     * 不创建对象的写
     */
    @Test
    public void noModleWrite() {
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList());
    }



    public List<ExcelDemoEntity> getDemoEntityData() {
        List<ExcelDemoEntity> demoEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoEntities.add(new ExcelDemoEntity("字符串" + i, new Date(), 0.56));
        }
        return demoEntities;
    }

    public List<LongestMatchColumnWidthData> getLongestMatchColumnWidthData() {
        List<LongestMatchColumnWidthData> demoEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoEntities.add(new LongestMatchColumnWidthData("字符串" + i, new Date(), 0.56));
        }
        return demoEntities;
    }

    public List<ConverterData> getConverterData() {
        List<ConverterData> demoEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoEntities.add(new ConverterData("字符串" + i, new Date(), 0.56));
        }
        return demoEntities;
    }

    public List<IndexData> getIndexData() {
        List<IndexData> demoEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoEntities.add(new IndexData("字符串" + i, new Date(), 0.56));
        }
        return demoEntities;
    }

    public List<ComplexHeadData> getComplexHeadData() {
        List<ComplexHeadData> demoEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoEntities.add(new ComplexHeadData("字符串" + i, new Date(), 0.56));
        }
        return demoEntities;
    }

    public List<WidthAndHeightData> getWidthAndHeightData() {
        List<WidthAndHeightData> demoEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            demoEntities.add(new WidthAndHeightData("字符串" + i, new Date(), 0.56));
        }
        return demoEntities;
    }


    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }



}
