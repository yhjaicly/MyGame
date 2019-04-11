package MyGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class Testywzq extends JFrame {

    public Testywzq() {

        Qipan qp = new Qipan();
        this.add(qp);
        this.setSize(580, 630);//面板尺寸
        this.setVisible(true);//面板处于可见状态
        this.setLocationRelativeTo(null);//面板居中
        this.setTitle("五子棋");
        this.setResizable(false);//禁止巨大化
        //选项栏
        JMenuBar jmb = new JMenuBar();//实例化菜单栏对象

        JMenu jm1 = new JMenu("选项");//实例化菜单对象
        JMenuItem jmt1_1 = new JMenuItem("重新开始");
        JMenuItem jmt1_2 = new JMenuItem("排行榜");
        JMenuItem jmt1_3 = new JMenuItem("退出游戏");

        JMenu jm2 = new JMenu("设置");
        JMenuItem jmt2_1 = new JMenuItem("更换棋盘");
        JMenuItem jmt2_2 = new JMenuItem("更换棋子");

        JMenu jm3 = new JMenu("帮助");
        JMenuItem jmt3_1 = new JMenuItem("关于我们");

        jmb.add(jm1);//将菜单对象添加到菜单栏中
        jmb.add(jm2);
        jmb.add(jm3);
        jm1.add(jmt1_1);
        jm1.add(jmt1_2);
        jm1.add(jmt1_3);
        jm2.add(jmt2_1);
        jm2.add(jmt2_2);
        jm3.add(jmt3_1);

        this.setJMenuBar(jmb);//将菜单栏对象添加到面板中JFrame
        this.revalidate();//刷新页面

        this.addMouseListener(qp);//将对话框与棋盘连接

        //重新开始
        jmt1_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < qp.row; i++) {
                    for (int j = 0; j < qp.col; j++) {
                        qp.num[i][j] = 0;
                    }
                }
                qp.sfjx = true;
                repaint();
            }
        });
        //排行榜
        jmt1_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String phMsg = "排行榜\n" +
                        "局数    步数    结果";
                for (int i = 0; i < qp.phbList.size(); i++) {
                    PaiHangBang phb = qp.phbList.get(i);
                    phMsg = phMsg + "\n" + phb.getJushu() + "           " + phb.getBushu() + "         " + phb.getJieguo();


                }
                JOptionPane.showMessageDialog(null, phMsg);

            }
        });
        //退出游戏
        jmt1_3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        //更换棋盘
        jmt2_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random sjs = new Random();
                int qpjsj = sjs.nextInt(4);//包头不包围0,1,2,3.
                qp.qptupian = "0" + qpjsj + ".jpg";
                repaint();//刷新图片

            }
        });
        //更换棋子
        jmt2_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random qzRan = new Random();
                int qzsjs = qzRan.nextInt(5);//6张图片
                qp.qizi1Name = "00" + qzsjs + ".png";
                qp.qizi2Name = "00" + (qzsjs + 1) + ".png";
                repaint();

            }
        });
        //关于我们
        jmt3_1.addActionListener(new ActionListener() {//添加行为监听器

            @Override
            public void actionPerformed(ActionEvent e) {

                String message = "关于我们\n"
                        + "1.玩家先落子\n"
                        + "2.五个棋子连成一条线胜利\n"
                        + "制作人：尹海军";
                JOptionPane.showMessageDialog(null, message);
            }
        });

    }

    public static void main(String[] args) {
        Testywzq testwzq = new Testywzq();
    }
}