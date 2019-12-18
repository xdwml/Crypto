
/*
 * �ֱ����˫���Զ����㣬�����㣬�˷�����ĵ�������ʱ��
 * ʱ��ͳ�ƴ����
 */

import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import java.lang.reflect.Proxy;

public class computeOperation implements ComputeTime {
    public Element alpha,beta,g,g_alpha,h,g_hat_alpha,mul;
    public Pairing pairing;
    public computeOperation() {
        pairing = PairingFactory.getPairing("a.properties");//
        alpha = pairing.getZr().newElement().setToRandom();//���ѡȡ��ָ����
        beta = pairing.getZr().newElement().setToRandom();//���ѡȡ�Ħ�
        g = pairing.getG1().newElement().setToRandom();//G1������Ԫg
        g_alpha = g.duplicate().powZn(alpha);//g^��
     /*   System.out.println("alpha  =" + alpha);
        System.out.println("beta   =" + beta);
        System.out.println("g      =" + g);
        System.out.println("g_alpha=" + g_alpha );   */
        System.out.println("-------------------����ʱ��Ա�----------------------");
    }
    @Override
    public void computePair() {
        //System.out.println("-------------------˫���ԶԲ���----------------------");
        g_hat_alpha = pairing.pairing(g, g_alpha);//e(g,g)^��

        // System.out.println("g_hat_alpha=" + g_hat_alpha);

    }
    @Override
    public void computePowZ() {
        // System.out.println("-------------------������----------------------");
        h  = g.duplicate().powZn(beta);//h=g^��
        // System.out.println("h=" + h);
    }
    @Override
    public void computeMult() {
        // System.out.println("-------------------�˷�����----------------------");
        mul=h.mul(g_alpha);
        // System.out.println("h=" + h); //mul=g^����
    }
    public static void main(String[] args) {
        computeOperation com = new computeOperation();
        // ��̬����ͳ�Ƹ���������ʱ
        ComputeTime comtime;
        comtime = (ComputeTime) Proxy.newProxyInstance(
                computeOperation.class.getClassLoader(),
                new Class[] { ComputeTime.class }, new TimeCountProxyHandle(com));
        comtime.computePair();
        comtime.computePowZ();
        comtime.computeMult();
    }
}  