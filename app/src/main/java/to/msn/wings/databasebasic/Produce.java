package to.msn.wings.databasebasic;

/**
 * Created by hifumi on 2017/01/15.
 */

public class Produce {
    private int lotid;
    private int number;
    private int comsumer;
    private String quality;
    private String type;

    public int getLotid() {
        return lotid;
    }

    public void setLotid(int lotid) {
        this.lotid = lotid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getComsumer() {
        return comsumer;
    }

    public void setComsumer(int comsumer) {
        this.comsumer = comsumer;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}