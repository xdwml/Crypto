package IBE;
import it.unisa.dia.gas.jpbc.*;  
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;  
import java.lang.reflect.Proxy;  
  
public class BasicIdent2 implements Ident {  
  
    private Element s, r, P, Ppub, Su, Qu, V, T1, T2;  
    private Field G1, Zr;  
    private Pairing pairing;  
  
    public BasicIdent2() {  
        init();  
    }  
  
    /** 
    * ��ʼ�� 
    */  
    private void init() {  
        pairing = PairingFactory.getPairing("a.properties");//  
        PairingFactory.getInstance().setUsePBCWhenPossible(true);  
        checkSymmetric(pairing);  
        //������r��ʼ��ΪZr�е�Ԫ��  
        Zr = pairing.getZr();  
        r = Zr.newElement();  
        //������Ppub��Qu��Su��V��ʼ��ΪG1�е�Ԫ�أ�G1�Ǽӷ�Ⱥ  
        G1 = pairing.getG1();  
        Ppub = G1.newElement();  
        Qu = G1.newElement();  
        Su = G1.newElement();  
        V = G1.newElement();  
        //������T1��T2V��ʼ��ΪGT�е�Ԫ�أ�GT�ǳ˷�Ⱥ  
        Field GT = pairing.getGT();  
        T1 = GT.newElement();  
        T2 = GT.newElement();  
    }  
  
    /** 
     * �ж�����Ƿ�Ϊ�Գ���ԣ����Գ������������Ϣ 
     *  
     * @param pairing 
     */  
    private void checkSymmetric(Pairing pairing) {  
        if (!pairing.isSymmetric()) {  
            throw new RuntimeException("��Կ���Գ�!");  
        }  
    }  
  
    @Override  
    public void buildSystem() {  
        System.out.println("-------------------ϵͳ�����׶�----------------------");  
        s = Zr.newRandomElement().getImmutable();// //�����������Կs  
        P = G1.newRandomElement().getImmutable();// ����G1������ԪP  
        Ppub = P.mulZn(s);// ����Ppub=sP,ע��˳��  
        System.out.println("P=" + P);  
        System.out.println("s=" + s);  
        System.out.println("Ppub=" + Ppub);  
    }  
  
    @Override  
    public void extractSecretKey() {  
        System.out.println("-------------------��Կ��ȡ�׶�----------------------");  
        Qu = pairing.getG1().newElement().setFromHash("IDu".getBytes(), 0, 3)  
                .getImmutable();// //�ӳ���Ϊ3��HashֵIDuȷ���û�U�����Ĺ�ԿQu  
        Su = Qu.mulZn(s).getImmutable();  
        System.out.println("Qu=" + Qu);  
        System.out.println("Su=" + Su);  
    }  
  
    @Override  
    public void encrypt() {  
        System.out.println("-------------------���ܽ׶�----------------------");  
        r = Zr.newRandomElement().getImmutable();  
        V = P.mulZn(r);  
        T1 = pairing.pairing(Ppub, Qu).getImmutable();// ����e��Ppub,Qu��  
        T1 = T1.powZn(r).getImmutable();  
        System.out.println("r=" + r);  
        System.out.println("V=" + V);  
        System.out.println("T1=e��Ppub,Qu��^r=" + T1);  
    }  
  
    @Override  
    public void decrypt() {  
        System.out.println("-------------------���ܽ׶�----------------------");  
        T2 = pairing.pairing(V, Su).getImmutable();  
        System.out.println("e(V,Su)=" + T2);  
        int byt = V.getLengthInBytes();// ��V���ֽڳ��ȣ�������Ϣ����Ϊ128�ֽ�  
        System.out.println("�ı�����" + (byt + 128));  
    }  
  
    public static void main(String[] args) {  
        BasicIdent2 ident = new BasicIdent2();  
        // ��̬����ͳ�Ƹ���������ʱ  
        Ident identProxy = (Ident) Proxy.newProxyInstance(  
                BasicIdent2.class.getClassLoader(),  
                new Class[] { Ident.class }, new TimeCountProxyHandle(ident));  
  
        identProxy.buildSystem();  
        identProxy.extractSecretKey();  
        identProxy.encrypt();  
        identProxy.decrypt();  
    }  
  
}  