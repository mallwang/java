/*
 * Decompiled with CFR 0.152.
 */
package steuerung;

public class Setting {
    private String refresh;
    private String worktime;

    public String getRefresh() {
        return this.refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getWorktime() {
        return this.worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String toString() {
        return "Setting [refresh=" + this.refresh + ", worktime=" + this.worktime + "]";
    }
}

