package ComputeTime;
/*
 * �ֱ����˫���Զ����㣬�����㣬�˷�����ĵ�������ʱ��
 */
import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class computeOperation2 implements ComputeTime2 {
    public Element alpha,beta,g,g_alpha,h,g_hat_alpha,mul;
    public Pairing pairing;
    public computeOperation2() {
        pairing = PairingFactory.getPairing("a.properties");//
        alpha = pairing.getZr().newElement().setToRandom();//���ѡȡ��ָ����
        beta = pairing.getZr().newElement().setToRandom();//���ѡȡ�Ħ�
        g = pairing.getG1().newElement().setToRandom();//G1������Ԫg
        g_alpha = g.duplicate().powZn(alpha);//g^��
        /*System.out.println("alpha  =" + alpha);
        System.out.println("beta   =" + beta);
        System.out.println("g      =" + g);
        System.out.println("g_alpha=" + g_alpha );    */
        System.out.println("-------------------��������ʱ��Ա�----------------------");
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
        computeOperation2 com=new computeOperation2();

        long startTime=System.currentTimeMillis();//��õ�ǰʱ��
        com.computePair();
        long endTime=System.currentTimeMillis();//��õ�ǰʱ��
        System.out.println("˫��������ʱ��Ϊ��"+(endTime-startTime)+"ms");

        long startTime2=System.currentTimeMillis();//��õ�ǰʱ��
        com.computePowZ();
        long endTime2=System.currentTimeMillis();//��õ�ǰʱ��
        System.out.println("���������ʱ��Ϊ��"+(endTime2-startTime2)+"ms");

        long startTime3=System.currentTimeMillis();//��õ�ǰʱ��
        com.computeMult();
        long endTime3=System.currentTimeMillis();//��õ�ǰʱ��
        System.out.println("�˷�������ʱ��Ϊ��"+(endTime3-startTime3)+"ms");
    }
}  