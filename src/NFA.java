import java.util.*;
public class NFA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char cin[]=Stringinput();
		//初始化状态转移集合
		ArrayList<State_trans> State_trans_list=State_trans_init();
		//初始化状态集合
		HashSet<String> State_hs = new HashSet<String>();
		//初始化辅助状态集合
		HashSet<String> State_hs_tem = new HashSet<String>();
		//输出状态转移表
		//State_trans_print(State_trans_list);
		//判断字符串是否被接受
		
		boolean result=Stringaccept(cin,State_trans_list,State_hs,State_hs_tem);
		
		if (result) {
			System.out.println("ε闭包集合中包含接受状态 q4，该字符串被状态机接收");
		}else {
			System.out.println("ε闭包集合中不包含接受状态 q4，该字符串被状态机拒绝");
		}
		System.out.println("------------------END-------------------");
	}
	//初始化状态转移关系
	public static ArrayList<State_trans> State_trans_init() {
		//将拒绝状态用NO表示，将空串用#表示
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
		System.out.println("-----------------状态转移关系----------------");
		System.out.println("-----当前状态--------字符--------转移状态-----");
		for(Iterator iterator=State_trans_list.iterator();iterator.hasNext();)
		{
			State_trans tran=(State_trans)iterator.next();
			System.out.println("-------"+tran.getOldState()+"------------"+tran.getTrans()+"------------"+tran.getNewState()+"--------");
		}
		
	}
	//接收字符串
	public static char[] Stringinput()
	{
		System.out.println("请输入仅含[0,1](包括空#)待识别的字符串：");
		//将字符存入字符数组中
		Scanner sc = new Scanner(System.in);
		String str=sc.next();
		char cin[] = str.toCharArray();//利用toCharArray方法转换
		sc.close();
		return cin;
	}
	//判断字符串是否被接受
	public static boolean Stringaccept(char cin[],ArrayList<State_trans> State_trans_list,HashSet<String> State_hs,HashSet<String> State_hs_tem)
	{
		//起始状态
		String beginState="q1";
		//终止状态
		String endState="q4";
		//初始化状态对象
		State st=new State(beginState,endState);
		//将初始状态放入hashset中
		State_hs.add(st.getBeginState());
		//从前到后依次遍历读取字符
		for (int i = 0; i < cin.length; i++) {			
			//1.克隆集合，克隆前清空克隆的集合
			State_hs_tem.clear();
			State_hs_tem.addAll(State_hs);
			//2.用克隆的集合进行比较，在原来的集合中进行添加
			//3.用原来的集合进行输出判断
			System.out.println("--------------------------------------------");
			System.out.printf("识别第%d个字符%s",i+1,cin[i]);
			System.out.println();
			//遍历集合
			for (Iterator iterator = State_hs_tem.iterator(); iterator.hasNext();) {
				//获取集合中的状态
				String strState = (String) iterator.next();
				for (State_trans stateTr : State_trans_list)//遍历所有的状态
				{	
				    if(strState.equals(stateTr.getOldState())&&(cin[i]==stateTr.getTrans())) {
				    	if (!"NO".equals(stateTr.getNewState())) {//如果状态的转移不为空，将新状态添加到集
				    		State_hs.add(stateTr.getNewState());//将新的状态添加到集合，先将其记录下来
						}
				    }				
				}	
			}
			//单独处理，用来得到空闭包
			for (Iterator iterator = State_hs.iterator(); iterator.hasNext();) {
				//获取集合中的状态
				String strState = (String) iterator.next();
				for (State_trans stateTr : State_trans_list)//遍历所有情况
				{
				    if (strState.equals(stateTr.getOldState())&&('#'==stateTr.getTrans())) {
				    	if (!"NO".equals(stateTr.getNewState())) {//如果状态的转移不为空，将新状态添加到集
				    		State_hs.add(stateTr.getNewState());//将新的状态添加到集合，先将其记录下来
						}
					}				
				}	
			}
			
			System.out.print("识别当前字符:"+cin[i]+",得到的空闭包为{");
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
