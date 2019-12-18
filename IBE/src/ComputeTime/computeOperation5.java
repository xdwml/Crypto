package ComputeTime;
/*
 * �ֱ����˫���Զ����㣬G�������㣬G�г˷����㣬Z�������㣬Z�г˷������ƽ��1000������ʱ��
 */
import it.unisa.dia.gas.jpbc.*;  
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class computeOperation5 implements ComputeTime3 {  
    public Element alpha,beta,g,g_alpha,h,g_hat_alpha,mul,alpha_beta,alphabeta;  
    public Pairing pairing;  
    public computeOperation5() {  
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
    public void computePowG() {  
       // System.out.println("-------------------G������----------------------");  
        h  = g.duplicate().powZn(beta);//h=g^��
       // System.out.println("h=" + h); 
    }  
  
    @Override  
    public void computeMulG() {  
       // System.out.println("-------------------G�˷�����----------------------");  
        mul=h.mul(g_alpha);
       // System.out.println("h=" + h); //mul=g^����
    }  
    @Override  
    public void computePowZ() {  
       // System.out.println("-------------------Z������----------------------");  
    	alpha_beta  = alpha.duplicate().powZn(beta);//alpha_beta=��^��
       // System.out.println("alpha^beta=" + alpha_beta); 
    }  
  
    @Override  
    public void computeMulZ() {  
       // System.out.println("-------------------Z�˷�����----------------------");  
    	alphabeta=alpha.mul(beta);//alphabeta=����
       // System.out.println("alpha*beta=" + alphabeta); 
    }
    public static void main(String[] args) {  
    	computeOperation5 com=new computeOperation5();
    	
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
    		com.computePowG();
			long endTime2=System.currentTimeMillis();//��õ�ǰʱ��
			long time2=endTime2-startTime2;
			sum2=time2+sum2;
    	}
    	long atime2=sum2/1000;
		System.out.println("G��������ƽ��1000��ʱ��Ϊ��"+atime2+"ms"); 
		
		long sum3=0;
    	for(int i=0;i<1000;i++){
    		long startTime3=System.currentTimeMillis();//��õ�ǰʱ��
    		com.computeMulG();;
			long endTime3=System.currentTimeMillis();//��õ�ǰʱ��
			long time3=endTime3-startTime3;
			sum3=time3+sum3;
    	}
    	long atime3=sum3/1000;
		System.out.println("G�˷�����ƽ��1000��ʱ��Ϊ��"+atime3+"ms"); 
		
		long sum4=0;
    	for(int i=0;i<1000;i++){
    		long startTime4=System.currentTimeMillis();//��õ�ǰʱ��
    		com.computePowG();
			long endTime4=System.currentTimeMillis();//��õ�ǰʱ��
			long time4=endTime4-startTime4;
			sum4=time4+sum4;
    	}
    	long atime4=sum4/1000;
		System.out.println("Z��������ƽ��1000��ʱ��Ϊ��"+atime4+"ms"); 
		
		long sum5=0;
    	for(int i=0;i<1000;i++){
    		long startTime5=System.currentTimeMillis();//��õ�ǰʱ��
    		com.computeMulG();;
			long endTime5=System.currentTimeMillis();//��õ�ǰʱ��
			long time5=endTime5-startTime5;
			sum5=time5+sum5;
    	}
    	long atime5=sum5/1000;
		System.out.println("Z�˷�����ƽ��1000��ʱ��Ϊ��"+atime5+"ms"); 
    }
}  