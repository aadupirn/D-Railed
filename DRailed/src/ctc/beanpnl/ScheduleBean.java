package ctc.beanpnl;

/**
 *
 * @author novo
 */
public class ScheduleBean {
    private String MMQBH;  //
    private String SCCS;   //
    private String ZT;    //
    private String KHH;      //
    private String KHMC;  //
    private String BHFX;  //
    private String FXRQ;  //
    private String CJR;   //
    public ScheduleBean(){};
    public ScheduleBean(String MMQBH,String SCCS,String ZT,String KHH,String KHMC,String BHFX,String FXRQ,String CJR){
        this.MMQBH = MMQBH;
        this.SCCS = SCCS;
        this.ZT = ZT;
        this.KHH = KHH;
        this.KHMC = KHMC;
        this.BHFX = BHFX;
        this.FXRQ = FXRQ;
        this.CJR = CJR;
    };
    /**
     * @return the MMQBH
     */
    public String getMMQBH() {
        return MMQBH;
    }

    /**
     * @param MMQBH the MMQBH to set
     */
    public void setMMQBH(String MMQBH) {
        this.MMQBH = MMQBH;
    }

    /**
     * @return the SCCS
     */
    public String getSCCS() {
        return SCCS;
    }

    /**
     * @param SCCS the SCCS to set
     */
    public void setSCCS(String SCCS) {
        this.SCCS = SCCS;
    }

    /**
     * @return the ZT
     */
    public String getZT() {
        return ZT;
    }

    /**
     * @param ZT the ZT to set
     */
    public void setZT(String ZT) {
        this.ZT = ZT;
    }

    /**
     * @return the KHH
     */
    public String getKHH() {
        return KHH;
    }

    /**
     * @param string the KHH to set
     */
    public void setKHH(String string) {
        this.KHH = string;
    }

    /**
     * @return the KHMC
     */
    public String getKHMC() {
        return KHMC;
    }

    /**
     * @param KHMC the KHMC to set
     */
    public void setKHMC(String KHMC) {
        this.KHMC = KHMC;
    }

    /**
     * @return the BHFX
     */
    public String getBHFX() {
        return BHFX;
    }

    /**
     * @param BHFX the BHFX to set
     */
    public void setBHFX(String BHFX) {
        this.BHFX = BHFX;
    }

    /**
     * @return the FXRQ
     */
    public String getFXRQ() {
        return FXRQ;
    }

    /**
     * @param FXRQ the FXRQ to set
     */
    public void setFXRQ(String FXRQ) {
        this.FXRQ = FXRQ;
    }

    /**
     * @return the CJR
     */
    public String getCJR() {
        return CJR;
    }

    /**
     * @param CJR the CJR to set
     */
    public void setCJR(String CJR) {
        this.CJR = CJR;
    }
    
}
