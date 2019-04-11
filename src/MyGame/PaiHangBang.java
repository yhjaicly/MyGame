package MyGame;

public class PaiHangBang {
    private int jushu;    //局数
    private int bushu;    //步数
    private String jieguo;//结果

    //Source-getersandsetters-all-ok
    public int getJushu() {
        return jushu;
    }

    public void setJushu(int jushu) {
        if (jushu <= 1) {
            this.jushu = 1;
        }
        this.jushu = jushu;
    }

    public int getBushu() {
        return bushu;
    }

    public void setBushu(int bushu) {
        this.bushu = bushu;
    }

    public String getJieguo() {
        return jieguo;
    }

    public void setJieguo(String jieguo) {
        this.jieguo = jieguo;
    }


}
