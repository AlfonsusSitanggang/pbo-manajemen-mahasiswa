/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package id.alfonlevi.mahasiswa.view.matakuliahdosen;

import id.alfonlevi.mahasiswa.controller.MataKuliahDosenController;
import id.alfonlevi.mahasiswa.view.kelasdosen.KelasDosenPanel;
import id.alfonlevi.mahasiswa.view.matakuliah.*;
import com.formdev.flatlaf.FlatClientProperties;
import id.alfonlevi.mahasiswa.controller.MataKuliahController;
import id.alfonlevi.mahasiswa.data.model.Kelas;
import id.alfonlevi.mahasiswa.view.base.DisposableView;
import id.alfonlevi.mahasiswa.view.base.TabbedPaneHelper;
import id.alfonlevi.mahasiswa.view.base.TextUtils;
import id.alfonlevi.mahasiswa.view.editkelas.EditKelasDialog;
import id.alfonlevi.mahasiswa.view.editmatakuliah.EditMataKuliahDialog;
import id.alfonlevi.mahasiswa.view.kelas.KelasPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author levir
 */
public class MataKuliahDosenPanel extends javax.swing.JPanel implements MataKuliahDosenView, DisposableView {
    private JLabel mNameLabel = new JLabel("Nama");
    private JLabel mEmptyLabel =  new JLabel("Belum ada mata kelas", SwingConstants.CENTER);
    private MataKuliahDosenController mController;
    private TabbedPaneHelper mTabbedPaneHelper;

    /**
     * Creates new form MataKuliahPanel
     */
    public MataKuliahDosenPanel(String id) {
        initComponents();

        var box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        var titleBox = Box.createHorizontalBox();
        titleBox.setAlignmentX(0);
        box.add(titleBox);

        mNameLabel.putClientProperty(FlatClientProperties.STYLE_CLASS, "h2");
        titleBox.add(mNameLabel);
        titleBox.add(Box.createHorizontalGlue());

        var subtitle = new JLabel("Daftar Kelas");
        subtitle.putClientProperty(FlatClientProperties.STYLE_CLASS, "h3");
        subtitle.setAlignmentX(0);
        box.add(subtitle);

        mTabPane.putClientProperty(FlatClientProperties.TABBED_PANE_LEADING_COMPONENT, box);
        mTabPane.putClientProperty(FlatClientProperties.TABBED_PANE_MINIMUM_TAB_WIDTH, 175);

        mTabbedPaneHelper = new TabbedPaneHelper(mTabPane, (cid) -> {
            if (cid.equals("")) {
                return mEmptyLabel;
            }
            return new KelasDosenPanel(cid);
        });

        mController = new MataKuliahDosenController(this, id);
    }

    @Override
    public void setTitle(String title) {
        mNameLabel.setText(TextUtils.intoHtml(title));
    }

    @Override
    public void setKelasList(List<Kelas> data) {
        var items = new ArrayList<TabbedPaneHelper.Item>();
        if (data.isEmpty()) {
            items.add(new TabbedPaneHelper.Item("", "Masih Kosong"));
        } else {
            for (var kelas : data) {
                items.add(new TabbedPaneHelper.Item(kelas.getId(), kelas.getNama()));
            }
        }
        mTabbedPaneHelper.setItems(items);
    }

    @Override
    public void dispose() {
        mTabbedPaneHelper.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mTabPane = new javax.swing.JTabbedPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        mTabPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        add(mTabPane);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane mTabPane;
    // End of variables declaration//GEN-END:variables
}
