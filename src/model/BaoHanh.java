package model;

import java.sql.Date;

public class BaoHanh {
    private int mabh;
    private int mahd;
    private Date ngaylap;
    private Date ngayhethan;

    public int getMabh() {
        return mabh;
    }

    public void setMabh(int mabh) {
        this.mabh = mabh;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date ngaylap) {
        this.ngaylap = ngaylap;
    }

    public Date getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(Date ngayhethan) {
        this.ngayhethan = ngayhethan;
    }
}
