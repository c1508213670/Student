package symexample;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
class Cprocity//ʡ��ѡ�����
{
	String province;
	List<String> city=new ArrayList ();
}
class smallstudent//Hash���value��
{
	String sname;
	String sex;
	String province;
	String city;
	List<String> hobby=new ArrayList();
	String hobbyelse;
}

public class FrameMain extends JFrame {
	
	static public class Student
	{
		String sno;
		String sname;
		String sex;
		String province;
		String city;
		List<String> hobby=new ArrayList();
		String hobbyelse;
	}
	static smallstudent alertstudent(Student temp)//ͨ��student�ഴ����Ӧhash���value
	{
		smallstudent result=new smallstudent();
		result.sname=temp.sname;
		result.sex=temp.sex;
		result.province=temp.province;
		result.city=temp.city;
		result.hobby=temp.hobby;
		result.hobbyelse=temp.hobbyelse;
		return result;
	}
	public static String getUTF8XMLString(String xml) //���ļ������ַ�ת��Ϊutf-8����
	{
	    // A StringBuffer Object
	    StringBuffer sb = new StringBuffer();
	    sb.append(xml);
	    String xmString = "";
	    String xmlUTF8="";
	    try {
	    xmString = new String(sb.toString().getBytes("UTF-8"));
	    xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    }
	    // return to String Formed
	    return xmlUTF8;
	    }
	 
	static List<Student> allstudents=new ArrayList<Student>();	 
	
	static List<Student> allstudentcreate(String str)//��ȡtxt�ļ�����allstudents�б�
	{
		try
		{
			allstudents=new ArrayList<Student>();
			InputStreamReader fr = new InputStreamReader(new FileInputStream(str), "UTF-8");
			BufferedReader br=new BufferedReader(fr);
			String line=null;
			String []sp;
			int flag=0;
			Student studenttemp=new Student();
			while((line=br.readLine())!=null)
			{
				flag++;
				switch(flag%8)//ѭ���б��ļ������Ӧ����
				{
			    case 1 :
			       studenttemp.sno=line.split("��")[1];
			       break; 
			    case 2 :
			       studenttemp.sname=line.split("��")[1];
			       break; 
			    case 3:
			    	studenttemp.sex=line.split("��")[1];
			    	break;
			    case 4:
			    	studenttemp.province=line.split("��")[1];
			    	break;
			    case 5:
			    	studenttemp.city=line.split("��")[1];
			    	break;
			    case 6:
			    	sp=(line.split("��")[1]).split("\t");
			    	for(int t=0;t<sp.length;t++)
			    	{
			    		studenttemp.hobby.add(sp[t]);
			    	}
			    	break;
			    case 7:
			    	studenttemp.hobbyelse=line.split("��")[1];
			    	break;
			    case 0:
			    	allstudents.add(studenttemp);
			    	studenttemp=new Student();
			    default : break;
			}
		}
	}catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int i=0;i<allstudents.size();i++)
		{
			Student demo=allstudents.get(i);
			//System.out.print(demo.sno+demo.sname+demo.sex+demo.province+demo.city+demo.hobby.get(0)+demo.hobbyelse);
			
		}
		return allstudents;
			
	}
	static HashMap<String,smallstudent> gethash(List<Student> allstudents) //�����ϣ��
	{
		HashMap<String,smallstudent> map=new HashMap<>();
		
			for(int w=0;w<allstudents.size();w++)
			{
				map.put(allstudents.get(w).sno, alertstudent(allstudents.get(w)));
			}
		return map;
	}
	static List<String> findcity(String str)//Ѱ��ÿ��ʡ�ݶ�Ӧ�����б�
	{
		List<Cprocity> protemp=new ArrayList();
		protemp=creatP();
		List<String> result=new ArrayList();
		for(int i=0;i<protemp.size();i++)
		{
			if(str.equals(protemp.get(i).province))
			{
				
				result=protemp.get(i).city;
				break;
			}
		}
		return result;
	}
	static  List<Cprocity> creatP()//�Գ������ݴ���
	{
		 List<Cprocity> pcity=new ArrayList<Cprocity>();
		String temp1="����ʡ,"
				+"������,����ʡ,����ʡ,�㶫ʡ,����,����׳��������,����ʡ,����ʡ,�ӱ�ʡ,����ʡ,������ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,���ɹ�������,���Ļ���������,�ຣʡ,ɽ��ʡ,ɽ��ʡ,����ʡ,�Ϻ���,�Ĵ�ʡ,�����,����������,�½�ά���������,����ʡ,�㽭ʡ,������";
		String[] temp=temp1.split(",");
		String tem="������,"
				+"������,������,�Ϸ���,������,������,������,������,������,��ɽ��,������,��ɽ��,������,ͭ����,�ߺ���,������,������,������,������,��ƽ��,Ȫ����,������,������,������,������,������,������,������,��Ȫ��,���ϲ���������,��������,�����,������,���Ļ���������,¤����,ƽ����,������,��ˮ��,������,��Ҵ��,������,������,��ݸ��,������,��ɽ��,������,ï����,��Դ��,������,������,�ع���,"
				+"տ����,��Զ��,��β��,�Ƹ���,÷����,��ͷ��,������,������,��ɽ��,�麣��,�ӳ���,��ɫ��,������,������,������,������,���Ǹ���,�����,������,������,������,������,������,������,��˳��,�Ͻ���,������,ǭ�ϲ���������������,������,ǭ�������嶱��������,����ˮ��,ͭ����,ǭ���ϲ���������������,������,������,������,����,��ɳ��,������,������,�Ĳ���,��ָɽ��,������,�ȷ���,������,�е���,ʯ��ׯ��,������,��ˮ��,��̨��,"
				+"��ɽ��,�ػʵ���,�żҿ���,������,֣����,������,�ױ���,������,ʡֱϽ������λ,������,������,����Ͽ��,������,�����,ƽ��ɽ��,�����,������,�ܿ���,������,�����,פ�����,��������,�绯��,�ں���,������,��ľ˹��,ĵ������,�׸���,������,���������,��̨����,˫Ѽɽ��,������,Т����,������,��ʯ��,ʮ����,�˲���,������,��ʩ����������������,������,������,�Ƹ���,������,������,�人��,������,������,������,������,"
				+"��������������������,¦����,������,������,��ɳ��,��̶��,������,������,������,�żҽ���,�׳���,��ɽ��,������,�ӱ߳�����������,��ƽ��,������,ͨ����,��Դ��,��ԭ��,������,������,�γ���,����,������,��ͨ��,������,������,̩����,���Ƹ���,�Ͼ���,������,��Ǩ��,������,�˴���,������,������,ӥ̶��,������,��������,�Ž���,�ϲ���,Ƽ����,������,��ɽ��,������,������,��Ϫ��,������,Ӫ����,������,"
				+"������,��˳��,������,��«����,������,�̽���,������,�˰���,�����׶���,��ͷ��,�����,���ױ�����,������˹��,���ֹ�����,�����첼��,���ͺ�����,ͨ����,�ں���,��ԭ��,������,������,ʯ��ɽ��,������,�����ɹ������������,������,Ϋ����,������,������,��Ӫ��,̩����,��̨��,������,�ൺ��,������,������,������,�ĳ���,������,������,������,��ׯ��,�Ͳ���,��ͬ��,������,������,̫ԭ��,�˳���,�ٷ���,˷����,������,"
				+"������,������,��Ȫ��,������,������,μ����,������,������,ͭ����,������,������,�Ӱ���,������,�Ϻ���,������,�ɶ���,������,������,��ɽ��,�㰲��,��Ԫ��,������,�ϳ���,������,üɽ��,�ڽ���,��֦����,������,��ɽ����������,�Ű���,�˱���,������,�Թ���,�����,������,��֥��,�տ�����,ɽ����,�����յ���,��������,����̩����,��ͼʲ��,������,��������������,������,������,�������,��ʲ����,����������,�������,"
				+"������,ʯ������,���ǵ���,ͼľ�����,��³����,��³ľ����,������,�������,������,������,��ɽ��,��������������,�������������,��ӹ���������������,��˫���ɴ���������,������,�ٲ���,�º���徰����������,�ն���,������,��Ϫ��,��ͨ��,������,����,������,������,������,������,������,��ˮ��,̨����,������,��ɽ��,������";
		String stem="����ʡ,"
				+"����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,������,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,"
				+"�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,�㶫ʡ,����,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����׳��������,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,�ӱ�ʡ,�ӱ�ʡ,�ӱ�ʡ,�ӱ�ʡ,�ӱ�ʡ,�ӱ�ʡ,�ӱ�ʡ,�ӱ�ʡ,"
				+"�ӱ�ʡ,�ӱ�ʡ,�ӱ�ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,������ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,"
				+"����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,"
				+"����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���ɹ�������,���Ļ���������,���Ļ���������,���Ļ���������,���Ļ���������,���Ļ���������,�ຣʡ,�ຣʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,"
				+"ɽ��ʡ,ɽ��ʡ,ɽ��ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,�Ϻ���,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�Ĵ�ʡ,�����,����������,����������,����������,����������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,"
				+"�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,�½�ά���������,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,����ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,�㽭ʡ,������";
		String[] stem1=stem.split(",");
		String[] tem1=tem.split(",");
		for(int i=0;i<temp.length;i++)
		{
			Cprocity dom=new Cprocity();
			dom.province=temp[i];
			pcity.add(dom);
		}
		int i=0;
		int flag=0;
		while(flag<temp.length)
		{
			if(pcity.get(flag).province.equals(stem1[i]))
			{
				pcity.get(flag).city.add(tem1[i]);
				i++;
				if(i>=stem1.length)
				{
					break;
				}
			}
			else
			{
				flag++;
			}
		}
		
		return pcity;
	}
int[] flag= {0};
	//private static JFrame frame;
public static void main(String args[])
{
	FrameMain test=new FrameMain();
	test.setVisible(true);
}
	public FrameMain()
	{
		super();
		setResizable(false);
		setBounds(600,300,230,230);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu0=new JMenu("ע��");
		menuBar.add(menu0);
		JMenu menu1=new JMenu("��ѯ");
		menuBar.add(menu1);
		JMenu menu2=new JMenu("�˳�");
		menuBar.add(menu2);
		menu0.addMenuListener(new myMenuListener());
		menu1.addMenuListener(new myMenuListener1());
		menu2.addMenuListener(new myMenuListener2());
	}
	private class myMenuListener  implements MenuListener
	{
		public void menuSelected(MenuEvent e) 
		{
			FrameRegister jsq=new FrameRegister();
			jsq.setVisible(true);
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class myMenuListener1  implements MenuListener
	{
		public void menuSelected(MenuEvent e) 
		{
			FrameBrowse jsq=new FrameBrowse();
			jsq.setVisible(true);
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private class myMenuListener2  implements MenuListener
	{
		public void menuSelected(MenuEvent e) 
		{
			Frameexit jsq=new Frameexit();
			jsq.setVisible(true);
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			
			
		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

 	
	public class Frameexit extends JFrame//�˳���
	{
		 Frameexit()
		{
			
			super();
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(500,350,480,100);
			final FlowLayout flowLayout=new FlowLayout();
			flowLayout.setVgap(20);
			
			flowLayout.setHgap(50);
			flowLayout.setAlignment(FlowLayout.CENTER);
			final JPanel jpanel=new JPanel();
			jpanel.setLayout(flowLayout);
			getContentPane().add(jpanel,BorderLayout.CENTER);
			JLabel plabel=new JLabel();
			plabel.setText("�Ƿ���Ҫ�˳���");
			plabel.setFont(new Font("",Font.BOLD,20));
			jpanel.add(plabel);

			final JButton button=new JButton();
			button.setText("��");
			button.setFont(new Font("",Font.BOLD,15));
			jpanel.add(button);
			final JButton sbutton=new JButton();
			sbutton.setText("��");
			sbutton.setFont(new Font("",Font.BOLD,15));
			jpanel.add(sbutton);
			
			button.addActionListener(new ActionListener()//ǿ���˳�
			{
				public void actionPerformed(ActionEvent e)
				{ 
					System.exit(0);
				}});
			
			sbutton.addActionListener(new ActionListener()//ֻ�ر�ҳ��
			{
				public void actionPerformed(ActionEvent e)
				{ 
					dispose();
				}});
			
		}
		 
	}
	
	public class Frameperror extends JFrame//��ʾ��ѯ��ǰһ���
	{
		Frameperror()
		{
			super();
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(490,400,400,100);
			final FlowLayout flowLayout=new FlowLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(50);
			flowLayout.setAlignment(FlowLayout.CENTER);
			final JPanel jpanel=new JPanel();
			jpanel.setLayout(flowLayout);
			getContentPane().add(jpanel,BorderLayout.CENTER);
			JLabel plabel=new JLabel();
			plabel.setText("����Ϊ����\n��ǰһ�\n��˶Ժ��ѯ��");
			plabel.setFont(new Font("",Font.BOLD,15));
			jpanel.add(plabel);
		}
	}
	public class Framenerror extends JFrame//��ʾ��ѯ�޺�һ���
	{
		Framenerror()
		{
			super();
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(490,400,400,100);
			final FlowLayout flowLayout=new FlowLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(50);
			flowLayout.setAlignment(FlowLayout.CENTER);
			final JPanel jpanel=new JPanel();
			jpanel.setLayout(flowLayout);
			getContentPane().add(jpanel,BorderLayout.CENTER);
			JLabel plabel=new JLabel();
			plabel.setText("����Ϊ�����޺�һ���˶Ժ��ѯ��");
			plabel.setFont(new Font("",Font.BOLD,15));
			jpanel.add(plabel);
		}
	}
	public class FrameBrowse extends JFrame//��ѯ��
	{
		FrameBrowse()
		{
			
			super();
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(350,0,700,850);
			final FlowLayout flowLayout=new FlowLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(50);
			flowLayout.setAlignment(FlowLayout.LEFT);
			final JPanel jpanel=new JPanel();
			jpanel.setLayout(flowLayout);
			getContentPane().add(jpanel,BorderLayout.CENTER);
			
//
			allstudents=allstudentcreate("F:\\java\\guiprj\\student.txt");
			HashMap<String,smallstudent> tempmap=new HashMap<>();
			tempmap=gethash(allstudents);
			List<HashMap> xxx=new ArrayList();
			xxx.add(tempmap);
			final JLabel toplabel=new JLabel();
			toplabel.setBorder(new TitledBorder(null,"",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,null,null));
			toplabel.setForeground(new Color(250,125,200));
			toplabel.setFont(new Font("",Font.BOLD,52));
			toplabel.setText("   �� ѯ ϵ ͳ   ");
			jpanel.add(toplabel);
			  final JLabel leftLabel=new JLabel(); 
			  leftLabel.setPreferredSize(new Dimension(10,0));
			  getContentPane().add(leftLabel,BorderLayout.NORTH);
//
			final JLabel label0=new JLabel();
			label0.setText("ѧ�ţ�");
			jpanel.add(label0);
			label0.setFont(new Font("",Font.BOLD,30));
			JTextField textField=new JTextField();
			textField.setPreferredSize(new Dimension (220,35));
			textField.setHorizontalAlignment(JTextField.CENTER);
			textField.setFont(new Font("",Font.BOLD,30));
			jpanel.add(textField);
//			JButton a=new JButton("     ");
//		    a.setContentAreaFilled(false);
//		  	a.setBorderPainted(false);
//			a.setEnabled(false);
//			jpanel.add(a);
			final JLabel label1=new JLabel();
			label1.setText("������");
			jpanel.add(label1);
			label1.setFont(new Font("",Font.BOLD,30));
			JTextField textField1=new JTextField();
			textField1.setPreferredSize(new Dimension (220,35));
			textField1.setHorizontalAlignment(JTextField.CENTER);
			textField1.setFont(new Font("",Font.BOLD,30));
			jpanel.add(textField1);
//			
			final JLabel label=new JLabel();
			label.setText("�Ա�");
			label.setFont(new Font("",Font.BOLD,30));
			jpanel.add(label);
			ButtonGroup buttonGroup=new ButtonGroup();
			final JRadioButton manRadioButton=new JRadioButton();
			buttonGroup.add(manRadioButton);
			//manRadioButton.setSelected(true);
			manRadioButton.setText("��");
			manRadioButton.setFont(new Font("",Font.BOLD,30));
			jpanel.add(manRadioButton);
			final JRadioButton woRadioButton=new JRadioButton();
			buttonGroup.add(woRadioButton);
			woRadioButton.setSelected(true);
			woRadioButton.setText("Ů");
			woRadioButton.setFont(new Font("",Font.BOLD,30));
			jpanel.add(woRadioButton);
			JButton c=new JButton("           ");
		    c.setContentAreaFilled(false);
		  	c.setBorderPainted(false);
			c.setEnabled(false);
			jpanel.add(c);
			
//
			List<Cprocity> protemp=new ArrayList<Cprocity>();
			protemp=creatP();
			JComboBox provinceBox=new JComboBox();
			JComboBox cityBox=new JComboBox();
			JLabel provincelabel=new JLabel();
			provincelabel.setText("ʡ�ݣ�");
			provincelabel.setFont(new Font("",Font.BOLD,30));
			jpanel.add(provincelabel);
			provinceBox.setEditable(true);
			provinceBox.setFont(new Font("",Font.BOLD,25));
			provinceBox.setPreferredSize(new Dimension (220,35));
			for(int i=0;i<protemp.size();i++)
			{
				provinceBox.addItem(protemp.get(i).province);
			}
			provinceBox.addActionListener(new ActionListener()//ʡ�м��������¼�
					{
						public void actionPerformed(ActionEvent e)
						{
							cityBox.removeAllItems();
							String idpro= provinceBox.getSelectedItem().toString();
							List<String> cityname=new ArrayList();
							cityname=findcity(idpro);
							for(int i=0;i<cityname.size();i++)
							{
								cityBox.addItem(cityname.get(i));
							}
							repaint();
						}
					});
			jpanel.add(provinceBox);
			JLabel city_label=new JLabel();
			city_label.setText("���У�");
			city_label.setFont(new Font("",Font.BOLD,30));
			jpanel.add(city_label);
			cityBox.setEditable(true);
			cityBox.setFont(new Font("",Font.BOLD,25));
			cityBox.setPreferredSize(new Dimension (220,35));
			jpanel.add(cityBox);
			
//			
			JLabel checkboxlabel=new JLabel();
			checkboxlabel.setText("���ã�");
			checkboxlabel.setFont(new Font("",Font.BOLD,30));
			jpanel.add(checkboxlabel);
			final JCheckBox readingbox=new JCheckBox();
			final JCheckBox listenbox=new JCheckBox();
			final JCheckBox ballbox=new JCheckBox();
			final JCheckBox drawbox=new JCheckBox();
			final JCheckBox instrumentbox=new JCheckBox();
			final JCheckBox pcbox=new JCheckBox();
			final JCheckBox elsebox=new JCheckBox();
			readingbox.setText("����");
			readingbox.setFont(new Font("",Font.BOLD,30));			
			listenbox.setText("������");
			listenbox.setFont(new Font("",Font.BOLD,30));
			ballbox.setText("�����˶�");
			ballbox.setFont(new Font("",Font.BOLD,30));
			drawbox.setText("�滭");
			drawbox.setFont(new Font("",Font.BOLD,30));
			instrumentbox.setText("��������");
			instrumentbox.setFont(new Font("",Font.BOLD,30));
			pcbox.setText("������");
			pcbox.setFont(new Font("",Font.BOLD,30));
			elsebox.setText("������");
			elsebox.setFont(new Font("",Font.BOLD,30));
			jpanel.add(readingbox);
			jpanel.add(listenbox);
			jpanel.add(ballbox);
			jpanel.add(drawbox);
			jpanel.add(instrumentbox);
			jpanel.add(pcbox);
			jpanel.add(elsebox);
			
//
			JTextArea textarea=new JTextArea();
			textarea.setColumns(220);
			textarea.setRows(30);
			textarea.setLineWrap(true);
			final JScrollPane scrollpane=new JScrollPane();
			scrollpane.setViewportView(textarea);
			scrollpane.setPreferredSize(new Dimension(250,80));
			jpanel.add(scrollpane);
//
			int step=0;
			int[] Lstep= {step};
			final JButton button=new JButton();
			button.setText("previous");
			button.setFont(new Font("",Font.BOLD,36));
			jpanel.add(button);
			final JButton sbutton=new JButton();
			sbutton.setText("next");
			sbutton.setFont(new Font("",Font.BOLD,36));
			jpanel.add(sbutton);
			
//
			
			button.addActionListener(new ActionListener()//ǰһ���¼�
			{
				public void actionPerformed(ActionEvent e)
				{
					if(Lstep[0]==0)//����flagͬ����¼��ֹԽ��
					{
						Frameperror wq=new Frameperror();
						wq.setVisible(true);
					}
					else
					{
					Lstep[0]--;
					if(Lstep[0]==0)//��ֹ��һ�������һ��Խ��
					{
						Lstep[0]=1;
						Frameperror wq=new Frameperror();
						wq.setVisible(true);
					}
					else {
					HashMap<String,smallstudent> mapp=xxx.get(0);
					textField.setText(allstudents.get(Lstep[0]-1).sno);
					textField1.setText(mapp.get(allstudents.get(Lstep[0]-1).sno).sname);
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).sex.equals("��"))
					{
						manRadioButton.setSelected(true);
					}
					else
					{
						woRadioButton.setSelected(true);
					}
					provinceBox.setSelectedItem(mapp.get(allstudents.get(Lstep[0]-1).sno).province);
					cityBox.setSelectedItem(mapp.get(allstudents.get(Lstep[0]-1).sno).city);
					for(int s=0;s<mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.size();s++)
					{
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("����"))
					{
						readingbox.setSelected(true);
					}
					if(allstudents.get(Lstep[0]-1).hobby.get(s).equals("������"))
					{
						listenbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("�����˶�"))
					{
						ballbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("�滭"))
					{
						drawbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("��������"))
					{
						instrumentbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("������"))
					{
						pcbox.setSelected(true);
					}
					}
					if(!(mapp.get(allstudents.get(Lstep[0]-1).sno).hobbyelse.equals("��")))
					{
						elsebox.setSelected(true);
						textarea.setText(mapp.get(allstudents.get(Lstep[0]-1).sno).hobbyelse);;
					}
					else {
						elsebox.setSelected(false);
						textarea.setText("");
						}
				}
					}
				}
				
			});

			sbutton.addActionListener(new ActionListener()//���ͬbutton
			{
				public void actionPerformed(ActionEvent e)
				{
					HashMap<String,smallstudent> mapp=xxx.get(0);
					if(Lstep[0]==mapp.size())
					{
						Framenerror wq=new Framenerror();
						wq.setVisible(true);
					}
					else
					{
					Lstep[0]++;
					
					
					textField.setText(allstudents.get(Lstep[0]-1).sno);
					textField1.setText(mapp.get(allstudents.get(Lstep[0]-1).sno).sname);
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).sex.equals("��"))
					{
						manRadioButton.setSelected(true);
					}
					else
					{
						woRadioButton.setSelected(true);
					}
					provinceBox.setSelectedItem(mapp.get(allstudents.get(Lstep[0]-1).sno).province);
					cityBox.setSelectedItem(mapp.get(allstudents.get(Lstep[0]-1).sno).city);
					for(int s=0;s<mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.size();s++)
					{
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("����"))
					{
						readingbox.setSelected(true);
					}
					if(allstudents.get(Lstep[0]-1).hobby.get(s).equals("������"))
					{
						listenbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("�����˶�"))
					{
						ballbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("�滭"))
					{
						drawbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("��������"))
					{
						instrumentbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("������"))
					{
						pcbox.setSelected(true);
					}
					}
					if(!(mapp.get(allstudents.get(Lstep[0]-1).sno).hobbyelse.equals("��")))
					{
						elsebox.setSelected(true);
						textarea.setText(mapp.get(allstudents.get(Lstep[0]-1).sno).hobbyelse);
					}
					else {
					elsebox.setSelected(false);
					textarea.setText("");
					}
				}
				}
				
			});
//�������ұ߾ౣ������	
	  final JLabel leftLabel1=new JLabel(); 
	  leftLabel1.setPreferredSize(new Dimension(100,0));
	  getContentPane().add(leftLabel1,BorderLayout.WEST);
	  final JLabel rightLabel=new JLabel(); 
	  rightLabel.setPreferredSize(new Dimension(100,0));
	  getContentPane().add(rightLabel,BorderLayout.EAST);
		}
	}
	public class FrameRegister extends JFrame 
	{
		FrameRegister()
		{
			
			super();
			
			FrameRegister jp=(FrameRegister)this;
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(350,0,700,850);
			final FlowLayout flowLayout=new FlowLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(50);
			flowLayout.setAlignment(FlowLayout.LEFT);
			final JPanel jpanel=new JPanel();
			jpanel.setLayout(flowLayout);
			getContentPane().add(jpanel,BorderLayout.CENTER);
			
//
			final JLabel toplabel=new JLabel();
			toplabel.setBorder(new TitledBorder(null,"",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,null,null));
			toplabel.setForeground(new Color(100,125,200));
			toplabel.setFont(new Font("",Font.BOLD,52));
			toplabel.setText("    ע �� ϵ ͳ   ");
			
			jpanel.add(toplabel);
			  final JLabel leftLabel=new JLabel(); 
			  leftLabel.setPreferredSize(new Dimension(10,0));
			  getContentPane().add(leftLabel,BorderLayout.NORTH);
			  //
				final JLabel label0=new JLabel();
				label0.setText("ѧ�ţ�");
				jpanel.add(label0);
				label0.setFont(new Font("",Font.BOLD,30));
				JTextField textField=new JTextField();
				textField.setPreferredSize(new Dimension (220,35));
				textField.setHorizontalAlignment(JTextField.CENTER);
				textField.setFont(new Font("",Font.BOLD,30));
				jpanel.add(textField);
//				JButton a=new JButton("     ");
//			    a.setContentAreaFilled(false);
//			  	a.setBorderPainted(false);
//				a.setEnabled(false);
//				jpanel.add(a);
				final JLabel label1=new JLabel();
				label1.setText("������");
				jpanel.add(label1);
				label1.setFont(new Font("",Font.BOLD,30));
				JTextField textField1=new JTextField();
				textField1.setPreferredSize(new Dimension (220,35));
				textField1.setHorizontalAlignment(JTextField.CENTER);
				textField1.setFont(new Font("",Font.BOLD,30));
				jpanel.add(textField1);
//				
				final JLabel label=new JLabel();
				label.setText("�Ա�");
				label.setFont(new Font("",Font.BOLD,30));
				jpanel.add(label);
				ButtonGroup buttonGroup=new ButtonGroup();
				final JRadioButton manRadioButton=new JRadioButton();
				buttonGroup.add(manRadioButton);
				//manRadioButton.setSelected(true);
				manRadioButton.setText("��");
				manRadioButton.setFont(new Font("",Font.BOLD,30));
				jpanel.add(manRadioButton);
				final JRadioButton woRadioButton=new JRadioButton();
				buttonGroup.add(woRadioButton);
				woRadioButton.setSelected(true);
				woRadioButton.setText("Ů");
				woRadioButton.setFont(new Font("",Font.BOLD,30));
				jpanel.add(woRadioButton);
				JButton c=new JButton("           ");
			    c.setContentAreaFilled(false);
			  	c.setBorderPainted(false);
				c.setEnabled(false);
				jpanel.add(c);
				
	//
				List<Cprocity> protemp=new ArrayList<Cprocity>();
				protemp=creatP();
				JComboBox provinceBox=new JComboBox();
				JComboBox cityBox=new JComboBox();
				JLabel provincelabel=new JLabel();
				provincelabel.setText("ʡ�ݣ�");
				provincelabel.setFont(new Font("",Font.BOLD,30));
				jpanel.add(provincelabel);
				provinceBox.setEditable(true);
				provinceBox.setFont(new Font("",Font.BOLD,25));
				provinceBox.setPreferredSize(new Dimension (220,35));
				for(int i=0;i<protemp.size();i++)
				{
					provinceBox.addItem(protemp.get(i).province);
				}
				provinceBox.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								cityBox.removeAllItems();
								String idpro= provinceBox.getSelectedItem().toString();
								List<String> cityname=new ArrayList();
								cityname=findcity(idpro);
								for(int i=0;i<cityname.size();i++)
								{
									cityBox.addItem(cityname.get(i));
								}
								repaint();
							}
						});
				jpanel.add(provinceBox);
				JLabel city_label=new JLabel();
				city_label.setText("���У�");
				city_label.setFont(new Font("",Font.BOLD,30));
				jpanel.add(city_label);
				cityBox.setEditable(true);
				cityBox.setFont(new Font("",Font.BOLD,25));
				cityBox.setPreferredSize(new Dimension (220,35));
				jpanel.add(cityBox);
				
//				
				JLabel checkboxlabel=new JLabel();
				checkboxlabel.setText("���ã�");
				checkboxlabel.setFont(new Font("",Font.BOLD,30));
				jpanel.add(checkboxlabel);
				final JCheckBox readingbox=new JCheckBox();
				final JCheckBox listenbox=new JCheckBox();
				final JCheckBox ballbox=new JCheckBox();
				final JCheckBox drawbox=new JCheckBox();
				final JCheckBox instrumentbox=new JCheckBox();
				final JCheckBox pcbox=new JCheckBox();
				final JCheckBox elsebox=new JCheckBox();
				readingbox.setText("����");
				readingbox.setFont(new Font("",Font.BOLD,30));			
				listenbox.setText("������");
				listenbox.setFont(new Font("",Font.BOLD,30));
				ballbox.setText("�����˶�");
				ballbox.setFont(new Font("",Font.BOLD,30));
				drawbox.setText("�滭");
				drawbox.setFont(new Font("",Font.BOLD,30));
				instrumentbox.setText("��������");
				instrumentbox.setFont(new Font("",Font.BOLD,30));
				pcbox.setText("������");
				pcbox.setFont(new Font("",Font.BOLD,30));
				elsebox.setText("������");
				elsebox.setFont(new Font("",Font.BOLD,30));
				jpanel.add(readingbox);
				jpanel.add(listenbox);
				jpanel.add(ballbox);
				jpanel.add(drawbox);
				jpanel.add(instrumentbox);
				jpanel.add(pcbox);
				jpanel.add(elsebox);
				
	//
				JTextArea textarea=new JTextArea();
				textarea.setColumns(220);
				textarea.setRows(30);
				textarea.setLineWrap(true);
				final JScrollPane scrollpane=new JScrollPane();
				scrollpane.setViewportView(textarea);
				scrollpane.setPreferredSize(new Dimension(250,80));
				jpanel.add(scrollpane);
			
//			
			final JButton button=new JButton();
			button.setText("����");
			button.setFont(new Font("����",Font.BOLD,36));
			jpanel.add(button);
			//button.addActionListener();
			JButton a=new JButton("   ");
		    a.setContentAreaFilled(false);
		  	a.setBorderPainted(false);
			a.setEnabled(false);
			jpanel.add(a);
			final JButton sbutton=new JButton();
			sbutton.setText("����");
			sbutton.setFont(new Font("����",Font.BOLD,36));
			jpanel.add(sbutton);
			
//
			button.addActionListener(new ActionListener()//����allstudents�б����
					{
						public void actionPerformed(ActionEvent e)
						{
							Student demo=new Student();
							demo.sno=textField.getText();
							demo.sname=textField1.getText();
							if(manRadioButton.isSelected())
							{
								demo.sex=manRadioButton.getText();
							}
							else
							{
								demo.sex="Ů";
							}
							demo.province=(String)provinceBox.getSelectedItem();
							demo.city=(String)cityBox.getSelectedItem();
							if(readingbox.isSelected())
							{
								demo.hobby.add(readingbox.getText());
							}
							if(listenbox.isSelected())
							{
								demo.hobby.add(listenbox.getText());
							}
							if(ballbox.isSelected())
							{
								demo.hobby.add(ballbox.getText());
							}
							if(drawbox.isSelected())
							{
								demo.hobby.add(drawbox.getText());
							}
							if(instrumentbox.isSelected())
							{
								demo.hobby.add(instrumentbox.getText());
							}
							if(pcbox.isSelected())
							{
								demo.hobby.add(pcbox.getText());
							}
							if(elsebox.isSelected())
							{
								demo.hobbyelse=textarea.getText();
							}
							if(!elsebox.isSelected())
							{
								demo.hobbyelse="��";
							}
							allstudents.add(demo);
							
							//System.out.print(demo.sno+demo.sname+demo.sex+demo.province+demo.city+demo.hobby.get(0)+demo.hobbyelse);
						}
						
					});
			sbutton.addActionListener(new ActionListener()//����txt�ļ����� 
					{
						@SuppressWarnings("resource")
						public void actionPerformed(ActionEvent e)
						{
							int Y=0;
							File file = new File("F:\\java\\guiprj\\student.txt");  
							if(!file.exists() || file.length() == 0) {  
							    Y=1;  
							}  
							try {
							Writer out;
				            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("F:\\java\\guiprj\\student.txt",true), "utf-8"));
				            if(Y==0) {out.write("\r\n");}
							for(int j=0;j<allstudents.size();j++) 
							{
								
							Student demo=new Student();
							demo=allstudents.get(j);
							out.write("ѧ�ţ�"+demo.sno+"\n������"+demo.sname+"\n�Ա�"+demo.sex+"\nʡ�ݣ�"+demo.province+"\n���У�"+demo.city+"\n���ã�");
							for(int i=0;i<demo.hobby.size();i++)
							{
								out.write(demo.hobby.get(i)+"\t");
							}
							out.write("\n������"+demo.hobbyelse+"\n");
							if(j!=allstudents.size()-1) {
							out.write("\r\n");}
							}
							out.write("//");
							out.close();
				            
						}catch( IOException eo)
							{
								eo.printStackTrace();
							}
						
		                dispose();
						}
						
					});
			
			  final JLabel leftLabel1=new JLabel(); 
			  leftLabel1.setPreferredSize(new Dimension(100,0));
			  getContentPane().add(leftLabel1,BorderLayout.WEST);
			  final JLabel rightLabel=new JLabel(); 
			  rightLabel.setPreferredSize(new Dimension(100,0));
			  getContentPane().add(rightLabel,BorderLayout.EAST);
			 
			
		}
	
	}
	
	}
