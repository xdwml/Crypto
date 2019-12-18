package ComputeTime;
/*
 * �ֱ����˫���Զ����㣬�����㣬�˷������ƽ��1000������ʱ��
 */
import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class computeOperation3 implements ComputeTime2 {
	public Element alpha,beta,g,g_alpha,h,g_hat_alpha,mul;
	public Pairing pairing;
	public computeOperation3() {
		pairing = PairingFactory.getPairing("a.properties");//
		alpha = pairing.getZr().newElement().setToRandom();//���ѡȡ�Ħ�
		beta = pairing.getZr().newElement().setToRandom();//���ѡȡ�Ħ�
		g = pairing.getG1().newElement().setToRandom();//G1������Ԫg
		g_alpha = g.duplicate().powZn(alpha);//g^��
       /* System.out.println("alpha  =" + alpha);
        System.out.println("beta   =" + beta);
        System.out.println("g      =" + g);
        System.out.println("g_alpha=" + g_alpha );  */
		System.out.println("-------------------ƽ��1000������ʱ��Ա�----------------------");
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
		computeOperation3 com=new computeOperation3();

		long sum1=0;
		for(int i=0;i<1000;i++){
			long startTime1=System.currentTimeMillis();//��õ�ǰʱ��
			com.computePair();
			long endTime1=System.currentTimeMillis();//��õ�ǰʱ��
			long time1=endTime1-startTime1;
			sum1=time1+sum1;
		}
		long atime1=sum1/1000;
		System.out.println("˫��������ƽ��1000��ʱ��Ϊ��"+atime1+"ms");

		long sum2=0;
		for(int i=0;i<1000;i++){
			long startTime2=System.currentTimeMillis();//��õ�ǰʱ��
			com.computePowZ();
			long endTime2=System.currentTimeMillis();//��õ�ǰʱ��
			long time2=endTime2-startTime2;
			sum2=time2+sum2;
		}
		long atime2=sum2/1000;
		System.out.println("���������ƽ��1000��ʱ��Ϊ��"+atime2+"ms");

		long sum3=0;
		for(int i=0;i<1000;i++){
			long startTime3=System.currentTimeMillis();//��õ�ǰʱ��
			com.computeMult();;
			long endTime3=System.currentTimeMillis();//��õ�ǰʱ��
			long time3=endTime3-startTime3;
			sum3=time3+sum3;
		}
		long atime3=sum3/1000;
		System.out.println("�˷�������ƽ��1000��ʱ��Ϊ��"+atime3+"ms");

	}
}  