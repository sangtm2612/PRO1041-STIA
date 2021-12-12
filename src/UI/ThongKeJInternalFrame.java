/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DAO.Models.ChiTietHangHoa;
import DAO.Models.HoaDon;
import Service.Implement.ChiTietHangHoaService;
import Service.Implement.HoaDonService;
import Utils.jdbcHelper;
import Utils.validateHelper;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author sangt
 */
public class ThongKeJInternalFrame extends javax.swing.JInternalFrame {

    List<ChiTietHangHoa> cthhList;
    ChiTietHangHoaService cthhService = new ChiTietHangHoaService();
    List<HoaDon> hdList;
    HoaDonService hdService = new HoaDonService();
    DefaultTableModel dtm;

    /**
     * Creates new form ThongKeJInternalFrame
     */
    public ThongKeJInternalFrame() {
        initComponents();
        init();
    }

    public void init() {
        dtm = (DefaultTableModel) tb_thongke.getModel();
        loadCBBNam();
        try {
            loadPNChart(2021);
            loadTableTK(2021);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTongTien() {
        int sum = 0;
        for (int i = 0; i < dtm.getRowCount(); i++) {
            String tienString = String.valueOf(tb_thongke.getValueAt(i, 1));
            int tien = Integer.parseInt(tienString);
            sum += tien;
        }
        return sum;
    }

    public void loadListNamHoaDon() {
        hdList = hdService.findNamHoaDonTK();
        for (HoaDon hd : hdList) {
            System.out.println(hd.getNgayTao());
        }
    }

    public String setVND(int tien) {
        Locale l = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(l);
        return format.format(tien);
    }

    public void loadCBBNam() {
        try {
            Connection conn = jdbcHelper.getConnect();
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT YEAR(NgayTao) FROM dbo.HoaDon");
            ResultSet rs = ps.executeQuery();
            cbb_nam.removeAllItems();
            while (rs.next()) {
                cbb_nam.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPNChart(int nam) {
        try {
            String sql = "SELECT * FROM dbo.f_thongkethang(" + nam + ")";
            JDBCCategoryDataset dataSet = new JDBCCategoryDataset(jdbcHelper.getConnect(), sql);
            JFreeChart fr = ChartFactory.createBarChart("Biểu đồ đường kê doanh thu", "Tháng", "VND", dataSet, PlotOrientation.VERTICAL, false, false, true);
            ChartPanel chartPanel = new ChartPanel(fr);
            chartPanel.setSize(896, 418);
            pn_bieudo.add(chartPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTableTK(int nam) {
        try {
            Connection conn = jdbcHelper.getConnect();
            String sql = "SELECT * FROM dbo.f_thongkethang(" + nam + ")";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            dtm.setRowCount(0);
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt(1), rs.getInt(2)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printPDF() {
        DefaultTableModel dtm = (DefaultTableModel) tb_thongke.getModel();
        JFileChooser fchoChooser = new JFileChooser();
        int result = fchoChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                if (!fchoChooser.getSelectedFile().toString().endsWith(".xlsx")) {
                    JOptionPane.showMessageDialog(this, "Không đúng định dạng!");
                    return;
                }
                File newFile = new File(fchoChooser.getSelectedFile().toString());
                FileOutputStream file = new FileOutputStream(newFile.getAbsoluteFile().getPath());
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet hangHoaSheet = wb.createSheet("TK_DoanhThu");
                XSSFRow row = hangHoaSheet.createRow((short) 0);
                XSSFCell h;

                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    h = row.createCell((short) i);
                    h.setCellValue(dtm.getColumnName(i));
                }
                XSSFRow row1;
                XSSFCell a1;
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    row1 = hangHoaSheet.createRow((short) i + 1);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        a1 = row1.createCell((short) j);
                        a1.setCellValue(String.valueOf(dtm.getValueAt(i, j)));
                    }
                }
                wb.write(file);
                wb.close();
                file.close();
                validateHelper.message(this, "Xuất file thành công!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
    
    public java.awt.Image getImageFromPanel(Component component) {

        BufferedImage image = new BufferedImage(component.getWidth(),
                component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_xuatExcel = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_thongke = new javax.swing.JTable();
        pn_bieudo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbb_nam = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lb_tongDoanhThu = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("THỐNG KÊ DOANH THU");
        setToolTipText("");

        btn_xuatExcel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_xuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.myPro.Icon/excel.png"))); // NOI18N
        btn_xuatExcel.setText("Xuất Excel");
        btn_xuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatExcelActionPerformed(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tb_thongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tháng", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_thongke);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bảng thống kê doanh thu", jPanel2);

        javax.swing.GroupLayout pn_bieudoLayout = new javax.swing.GroupLayout(pn_bieudo);
        pn_bieudo.setLayout(pn_bieudoLayout);
        pn_bieudoLayout.setHorizontalGroup(
            pn_bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 896, Short.MAX_VALUE)
        );
        pn_bieudoLayout.setVerticalGroup(
            pn_bieudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Biểu đồ thống kê doanh thu", pn_bieudo);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Năm:");

        cbb_nam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_namItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_nam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbb_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tổng doanh thu năm:");

        lb_tongDoanhThu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_tongDoanhThu.setForeground(new java.awt.Color(204, 0, 0));
        lb_tongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tongDoanhThu.setText("100,000,000 đ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_tongDoanhThu)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_tongDoanhThu)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_xuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatExcelActionPerformed
        printPDF();
    }//GEN-LAST:event_btn_xuatExcelActionPerformed

    private void cbb_namItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_namItemStateChanged
        String nam = cbb_nam.getSelectedItem().toString();
        int parseNam = Integer.parseInt(nam);
        loadPNChart(parseNam);
        loadTableTK(parseNam);
        lb_tongDoanhThu.setText(setVND(getTongTien()));
    }//GEN-LAST:event_cbb_namItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xuatExcel;
    private javax.swing.JComboBox<String> cbb_nam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb_tongDoanhThu;
    private javax.swing.JPanel pn_bieudo;
    private javax.swing.JTable tb_thongke;
    // End of variables declaration//GEN-END:variables

    private Object GetData(JTable tb_thongke, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
