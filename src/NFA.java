import java.util.*;
public class NFA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char cin[]=Stringinput();
		//��ʼ��״̬ת�Ƽ���
		ArrayList<State_trans> State_trans_list=State_trans_init();
		//��ʼ��״̬����
		HashSet<String> State_hs = new HashSet<String>();
		//��ʼ������״̬����
		HashSet<String> State_hs_tem = new HashSet<String>();
		//���״̬ת�Ʊ�
		//State_trans_print(State_trans_list);
		//�ж��ַ����Ƿ񱻽���
		
		boolean result=Stringaccept(cin,State_trans_list,State_hs,State_hs_tem);
		
		if (result) {
			System.out.println("�űհ������а�������״̬ q4�����ַ�����״̬������");
		}else {
			System.out.println("�űհ������в���������״̬ q4�����ַ�����״̬���ܾ�");
		}
		System.out.println("------------------END-------------------");
	}
	//��ʼ��״̬ת�ƹ�ϵ
	public static ArrayList<State_trans> State_trans_init() {
		//���ܾ�״̬��NO��ʾ�����մ���#��ʾ
		ArrayList<State_trans> list = new ArrayList<State_trans>();
		list.add(new State_trans("q1",'0',"q1"));
		list.add(new State_trans("q1",'1',"q1"));
		list.add(new State_trans("q1",'1',"q2"));
		list.add(new State_trans("q1",'#',"NO"));
		list.add(new State_trans("q2",'0',"q3"));
		list.add(new State_trans("q2",'1',"NO"));
		list.add(new State_trans("q2",'#',"q3"));
		list.add(new State_trans("q3",'0',"NO"));
		list.add(new State_trans("q3",'1',"q4"));
		list.add(new State_trans("q3",'#',"NO"));
		list.add(new State_trans("q4",'0',"q4"));
		list.add(new State_trans("q4",'1',"q4"));
		list.add(new State_trans("q4",'#',"NO"));
		return list;
	}
	
	public static void State_trans_print(ArrayList<State_trans> State_trans_list)
	{
		System.out.println("--------------------------------------------");
		System.out.println("-----------------״̬ת�ƹ�ϵ----------------");
		System.out.println("-----��ǰ״̬--------�ַ�--------ת��״̬-----");
		for(Iterator iterator=State_trans_list.iterator();iterator.hasNext();)
		{
			State_trans tran=(State_trans)iterator.next();
			System.out.println("-------"+tran.getOldState()+"------------"+tran.getTrans()+"------------"+tran.getNewState()+"--------");
		}
		
	}
	//�����ַ���
	public static char[] Stringinput()
	{
		System.out.println("���������[0,1](������#)��ʶ����ַ�����");
		//���ַ������ַ�������
		Scanner sc = new Scanner(System.in);
		String str=sc.next();
		char cin[] = str.toCharArray();//����toCharArray����ת��
		sc.close();
		return cin;
	}
	//�ж��ַ����Ƿ񱻽���
	public static boolean Stringaccept(char cin[],ArrayList<State_trans> State_trans_list,HashSet<String> State_hs,HashSet<String> State_hs_tem)
	{
		//��ʼ״̬
		String beginState="q1";
		//��ֹ״̬
		String endState="q4";
		//��ʼ��״̬����
		State st=new State(beginState,endState);
		//����ʼ״̬����hashset��
		State_hs.add(st.getBeginState());
		//��ǰ�������α�����ȡ�ַ�
		for (int i = 0; i < cin.length; i++) {			
			//1.��¡���ϣ���¡ǰ��տ�¡�ļ���
			State_hs_tem.clear();
			State_hs_tem.addAll(State_hs);
			//2.�ÿ�¡�ļ��Ͻ��бȽϣ���ԭ���ļ����н������
			//3.��ԭ���ļ��Ͻ�������ж�
			System.out.println("--------------------------------------------");
			System.out.printf("ʶ���%d���ַ�%s",i+1,cin[i]);
			System.out.println();
			//��������
			for (Iterator iterator = State_hs_tem.iterator(); iterator.hasNext();) {
				//��ȡ�����е�״̬
				String strState = (String) iterator.next();
				for (State_trans stateTr : State_trans_list)//�������е�״̬
				{	
				    if(strState.equals(stateTr.getOldState())&&(cin[i]==stateTr.getTrans())) {
				    	if (!"NO".equals(stateTr.getNewState())) {//���״̬��ת�Ʋ�Ϊ�գ�����״̬��ӵ���
				    		State_hs.add(stateTr.getNewState());//���µ�״̬��ӵ����ϣ��Ƚ����¼����
						}
				    }				
				}	
			}
			//�������������õ��ձհ�
			for (Iterator iterator = State_hs.iterator(); iterator.hasNext();) {
				//��ȡ�����е�״̬
				String strState = (String) iterator.next();
				for (State_trans stateTr : State_trans_list)//�����������
				{
				    if (strState.equals(stateTr.getOldState())&&('#'==stateTr.getTrans())) {
				    	if (!"NO".equals(stateTr.getNewState())) {//���״̬��ת�Ʋ�Ϊ�գ�����״̬��ӵ���
				    		State_hs.add(stateTr.getNewState());//���µ�״̬��ӵ����ϣ��Ƚ����¼����
						}
					}				
				}	
			}
			
			System.out.print("ʶ��ǰ�ַ�:"+cin[i]+",�õ��Ŀձհ�Ϊ{");
			for (String s : State_hs) {
				System.out.print(s+",");
			}
			System.out.println("}");
			System.out.println("--------------------------------------------");
		}
		if(State_hs.contains(st.getEndState()))
			return true;
		else
			return false;
	}

}
