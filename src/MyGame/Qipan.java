package MyGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Qipan extends JPanel implements MouseListener {//鼠标监听器

    String qptupian = "00.jpg";//更换棋盘图片
    String qizi1Name = "000.png";//更换棋子1图片
    String qizi2Name = "001.png";//更换棋子2图片

    int row = 15;//棋盘的行
    int col = 15;//期盘的列

    //定义一个二维数组，用0,1,2代表如下信息
    //0：代表该位置为空
    //1：代表该位置显示棋子1
    //2：代表该位置显示棋子2

    int[][] num = new int[row][col];//棋盘二维数组
    //定义一个集合对象，用于存储排行榜信息
    List<PaiHangBang> phbList = new ArrayList<PaiHangBang>();

    int bushu = 0;//令初始步数为0

    boolean sfjx = true;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //显示棋盘
        Image qpImg = new ImageIcon("image/" + qptupian).getImage();
        g.drawImage(qpImg, 0, 0, 567, 567, this);

        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                if (num[i][j] == 1) {
                    //显示棋子
                    Image qiziImg = new ImageIcon("image/" + qizi1Name).getImage();
                    g.drawImage(qiziImg, i * 35 + 20, j * 35 + 20, 35, 35, this);
                } else if (num[i][j] == 2) {

                    //显示棋子2
                    Image qizi2Img = new ImageIcon("image/" + qizi2Name).getImage();
                    g.drawImage(qizi2Img, i * 35 + 20, j * 35 + 20, 35, 35, this);
                }

            }
        }

    }

    public int[] tswz(int x, int y) {//特殊位置，为游戏添加难度
        int count = 1;
        int[] wz1 = null;
        int[] wz2 = null;
        //左
        for (int i = x - 1; i >= 0; i--) {
            if (num[i][y] == num[x][y]) {
                count++;
            } else {
                if (num[i][y] == 0) {
                    wz1 = new int[]{i, y};
                }
                break;
            }
        }
        //右
        for (int i = x + 1; i < row; i++) {
            if (num[i][y] == num[x][y]) {
                count++;
            } else {
                if (num[i][y] == 0) {
                    wz2 = new int[]{i, y};
                }
                break;
            }
        }
        //左右
        if (count >= 3) {
            if (wz1 != null) {
                return wz1;
            } else if (wz2 != null) {
                return wz2;
            } else {
                return null;
            }
        }
        //清空数据
        count = 1;
        wz1 = null;
        wz2 = null;


        //上
        for (int j = y - 1; j >= 0; j--) {
            if (num[x][j] == num[x][y]) {
                count++;
            } else {
                if (num[x][j] == 0) {
                    wz1 = new int[]{x, j};
                }
                break;
            }
        }
        //下
        for (int j = y + 1; j < col; j++) {
            if (num[x][j] == num[x][y]) {
                count++;
            } else {
                if (num[x][j] == 0) {
                    wz2 = new int[]{x, j};
                }
                break;
            }
        }
        //上下
        if (count >= 3) {
            if (wz1 != null) {
                return wz1;
            } else if (wz2 != null) {
                return wz2;
            } else {
                return null;
            }
        }
        //清空数据
        count = 1;
        wz1 = null;
        wz2 = null;


        //左上
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                if (num[i][j] == 0) {
                    wz1 = new int[]{i, j};
                }
                break;
            }
        }
        //右下
        for (int i = x + 1, j = y + 1; i < row && j < row; i++, j++) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                if (num[i][j] == 0) {
                    wz2 = new int[]{i, j};
                }
                break;
            }
        }
        //左上右下
        if (count >= 3) {
            if (wz1 != null) {
                return wz1;
            } else if (wz2 != null) {
                return wz2;
            } else {
                return null;
            }
        }
        //清空数据
        count = 1;
        wz1 = null;
        wz2 = null;


        //左下
        for (int i = x - 1, j = y + 1; i >= 0 && j < row; i--, j++) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                if (num[i][j] == 0) {
                    wz1 = new int[]{i, j};
                }
                break;
            }
        }
        //右上
        for (int i = x + 1, j = y - 1; i < row && j >= 0; i++, j--) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                if (num[i][j] == 0) {
                    wz2 = new int[]{i, j};
                }
                break;
            }
        }
        //左下右上
        if (count >= 3) {
            if (wz1 != null) {
                return wz1;
            } else if (wz2 != null) {
                return wz2;
            } else {
                return null;
            }
        }
        //清空数据
        count = 1;
        wz1 = null;
        wz2 = null;
        return null;

    }

    public boolean iswin(int x, int y) {//判断是否胜利
        //用于显示棋子的数量
        int count = 1;
        //左
        for (int i = x - 1; i >= 0; i--) {
            if (num[i][y] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //右
        for (int i = x + 1; i < 15; i++) {
            if (num[i][y] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //左右
        if (count >= 5) {
            return true;
        }
        //让棋子数恢复为1
        count = 1;
        //上
        for (int j = y - 1; j >= 0; j--) {
            if (num[x][j] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //下
        for (int j = y + 1; j < 15; j++) {
            if (num[x][j] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //上下
        if (count >= 5) {
            return true;
        }
        count = 1;
        //左上
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //右下
        for (int i = x + 1, j = y + 1; i < 15 && j < 150; i++, j++) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //左上右下
        if (count >= 5) {
            return true;
        }
        count = 1;
        //左下
        for (int i = x - 1, j = y + 1; i >= 0 && j < 15; i--, j++) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //右上
        for (int i = x + 1, j = y - 1; i < 15 && j >= 0; i++, j--) {
            if (num[i][j] == num[x][y]) {
                count++;
            } else {
                break;
            }
        }
        //左下右上
        if (count >= 5) {
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {//鼠标监听器
        if (sfjx) {
            Graphics g = this.getGraphics();
            int x = e.getX();
            int y = e.getY();
            int i = (x - 25) / 35;
            int j = (y - 75) / 35;
            if (num[i][j] != 0) {
                JOptionPane.showMessageDialog(null, "该位置有棋子，请重新落子！");
                return;
            }
            //显示棋子1
            Image qizi1Img = new ImageIcon("image/" + qizi1Name).getImage();
            g.drawImage(qizi1Img, i * 35 + 20, j * 35 + 20, 35, 35, this);
            bushu++;
            num[i][j] = 1;
            //当满足if条件时，才显示你赢了
            boolean shengli = iswin(i, j);
            if (shengli) {
                JOptionPane.showMessageDialog(null, "你赢了！");
                sfjx = false;
                PaiHangBang phb = new PaiHangBang();
                phb.setBushu(bushu);
                phb.setJieguo("胜利");
                phb.setJushu(phbList.size() + 1);
                phbList.add(phb);
                return;//结束程序
            }

            //线程，用来控制间隔时间
            try {
                Thread.sleep(1000);//休息1000ms
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Random qiRan = new Random();//随机显示
            do {
                int[] wz = tswz(i, j);
                if (wz == null) {
                    i = qiRan.nextInt(15);
                    j = qiRan.nextInt(15);
                } else {
                    i = wz[0];
                    j = wz[1];
                }
            } while (num[i][j] != 0);

            //棋子2
            Image qizi2Img = new ImageIcon("image/" + qizi2Name).getImage();
            g.drawImage(qizi2Img, i * 35 + 20, j * 35 + 20, 35, 35, this);
            num[i][j] = 2;
            //让电脑胜利的功能
            boolean dnsl = iswin(i, j);
            if (dnsl) {
                JOptionPane.showMessageDialog(null, "电脑胜利！");
                sfjx = false;
                PaiHangBang phb = new PaiHangBang();
                phb.setJushu(phbList.size() + 1);
                phb.setBushu(bushu);
                phb.setJieguo("失败");
                phbList.add(phb);
                return;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
