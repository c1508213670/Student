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
class Cprocity//省份选择框类
{
	String province;
	List<String> city=new ArrayList ();
}
class smallstudent//Hash表的value类
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
	static smallstudent alertstudent(Student temp)//通过student类创建对应hash表的value
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
	public static String getUTF8XMLString(String xml) //将文件读入字符转换为utf-8编码
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
	
	static List<Student> allstudentcreate(String str)//读取txt文件创建allstudents列表
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
				switch(flag%8)//循环判别文件读入对应数据
				{
			    case 1 :
			       studenttemp.sno=line.split("：")[1];
			       break; 
			    case 2 :
			       studenttemp.sname=line.split("：")[1];
			       break; 
			    case 3:
			    	studenttemp.sex=line.split("：")[1];
			    	break;
			    case 4:
			    	studenttemp.province=line.split("：")[1];
			    	break;
			    case 5:
			    	studenttemp.city=line.split("：")[1];
			    	break;
			    case 6:
			    	sp=(line.split("：")[1]).split("\t");
			    	for(int t=0;t<sp.length;t++)
			    	{
			    		studenttemp.hobby.add(sp[t]);
			    	}
			    	break;
			    case 7:
			    	studenttemp.hobbyelse=line.split("：")[1];
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
	static HashMap<String,smallstudent> gethash(List<Student> allstudents) //构造哈希表
	{
		HashMap<String,smallstudent> map=new HashMap<>();
		
			for(int w=0;w<allstudents.size();w++)
			{
				map.put(allstudents.get(w).sno, alertstudent(allstudents.get(w)));
			}
		return map;
	}
	static List<String> findcity(String str)//寻找每个省份对应城市列表
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
	static  List<Cprocity> creatP()//对城市数据处理
	{
		 List<Cprocity> pcity=new ArrayList<Cprocity>();
		String temp1="安徽省,"
				+"北京市,福建省,甘肃省,广东省,广西,广西壮族自治区,贵州省,海南省,河北省,河南省,黑龙江省,湖北省,湖南省,吉林省,江苏省,江西省,辽宁省,内蒙古自治区,宁夏回族自治区,青海省,山东省,山西省,陕西省,上海市,四川省,天津市,西藏自治区,新疆维吾尔自治区,云南省,浙江省,重庆市";
		String[] temp=temp1.split(",");
		String tem="安庆市,"
				+"蚌埠市,亳州市,合肥市,池州市,滁州市,阜阳市,淮北市,淮南市,黄山市,六安市,马鞍山市,宣城市,铜陵市,芜湖市,宿州市,北京市,宁德市,福州市,南平市,泉州市,漳州市,龙岩市,莆田市,三明市,厦门市,白银市,定西市,酒泉市,甘南藏族自治州,嘉峪关市,金昌市,兰州市,临夏回族自治州,陇南市,平凉市,庆阳市,天水市,武威市,张掖市,潮州市,广州市,东莞市,江门市,佛山市,肇庆市,茂名市,河源市,惠州市,揭阳市,韶关市,"
				+"湛江市,清远市,汕尾市,云浮市,梅州市,汕头市,深圳市,阳江市,中山市,珠海市,河池市,百色市,北海市,玉林市,梧州市,崇左市,防城港市,贵港市,桂林市,来宾市,贺州市,柳州市,南宁市,钦州市,安顺市,毕节市,遵义市,黔南布依族苗族自治州,贵阳市,黔东南苗族侗族自治州,六盘水市,铜仁市,黔西南布依族苗族自治州,儋州市,东方市,海口市,琼海市,三沙市,三亚市,万宁市,文昌市,五指山市,保定市,廊坊市,沧州市,承德市,石家庄市,邯郸市,衡水市,邢台市,"
				+"唐山市,秦皇岛市,张家口市,安阳市,郑州市,南阳市,鹤壁市,新乡市,省直辖行政单位,焦作市,开封市,三门峡市,洛阳市,漯河市,平顶山市,濮阳市,商丘市,周口市,信阳市,许昌市,驻马店市,哈尔滨市,绥化市,黑河市,大庆市,佳木斯市,牡丹江市,鹤岗市,鸡西市,齐齐哈尔市,七台河市,双鸭山市,伊春市,孝感市,咸宁市,黄石市,十堰市,宜昌市,鄂州市,恩施土家族苗族自治州,随州市,荆州市,黄冈市,荆门市,襄阳市,武汉市,常德市,衡阳市,郴州市,怀化市,"
				+"湘西土家族苗族自治州,娄底市,株洲市,岳阳市,长沙市,湘潭市,邵阳市,益阳市,永州市,张家界市,白城市,白山市,长春市,延边朝鲜族自治州,四平市,吉林市,通化市,辽源市,松原市,苏州市,常州市,盐城市,镇江市,扬州市,南通市,淮安市,无锡市,泰州市,连云港市,南京市,徐州市,宿迁市,上饶市,宜春市,抚州市,赣州市,鹰潭市,吉安市,景德镇市,九江市,南昌市,萍乡市,新余市,鞍山市,朝阳市,锦州市,本溪市,大连市,营口市,丹东市,"
				+"辽阳市,抚顺市,阜新市,葫芦岛市,铁岭市,盘锦市,沈阳市,兴安盟,巴彦淖尔市,包头市,赤峰市,呼伦贝尔市,鄂尔多斯市,锡林郭勒盟,乌兰察布市,呼和浩特市,通辽市,乌海市,固原市,银川市,吴忠市,石嘴山市,中卫市,海西蒙古族藏族自治州,西宁市,潍坊市,滨州市,德州市,东营市,泰安市,烟台市,菏泽市,青岛市,济南市,济宁市,莱芜市,聊城市,临沂市,日照市,威海市,枣庄市,淄博市,大同市,吕梁市,晋城市,太原市,运城市,临汾市,朔州市,晋中市,"
				+"长治市,忻州市,阳泉市,安康市,宝鸡市,渭南市,汉中市,商洛市,铜川市,西安市,咸阳市,延安市,榆林市,上海市,巴中市,成都市,达州市,德阳市,乐山市,广安市,广元市,绵阳市,南充市,泸州市,眉山市,内江市,攀枝花市,遂宁市,凉山彝族自治州,雅安市,宜宾市,资阳市,自贡市,天津市,拉萨市,林芝市,日喀则市,山南市,阿克苏地区,阿拉尔市,阿勒泰地区,阿图什市,博乐市,昌吉回族自治州,阜康市,哈密市,和田地区,喀什地区,克拉玛依市,库尔勒市,"
				+"奎屯市,石河子市,塔城地区,图木舒克市,吐鲁番市,乌鲁木齐市,乌苏市,五家渠市,伊宁市,昆明市,保山市,楚雄彝族自治州,大理白族自治州,红河哈尼族彝族自治州,西双版纳傣族自治州,丽江市,临沧市,德宏傣族景颇族自治州,普洱市,曲靖市,玉溪市,昭通市,宁波市,金华市,杭州市,嘉兴市,湖州市,衢州市,温州市,丽水市,台州市,绍兴市,舟山市,重庆市";
		String stem="安徽省,"
				+"安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,安徽省,北京市,福建省,福建省,福建省,福建省,福建省,福建省,福建省,福建省,福建省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,甘肃省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,"
				+"广东省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,广东省,广西,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,广西壮族自治区,贵州省,贵州省,贵州省,贵州省,贵州省,贵州省,贵州省,贵州省,贵州省,海南省,海南省,海南省,海南省,海南省,海南省,海南省,海南省,海南省,河北省,河北省,河北省,河北省,河北省,河北省,河北省,河北省,"
				+"河北省,河北省,河北省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,河南省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,黑龙江省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖北省,湖南省,湖南省,湖南省,湖南省,"
				+"湖南省,湖南省,湖南省,湖南省,湖南省,湖南省,湖南省,湖南省,湖南省,湖南省,吉林省,吉林省,吉林省,吉林省,吉林省,吉林省,吉林省,吉林省,吉林省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江苏省,江西省,江西省,江西省,江西省,江西省,江西省,江西省,江西省,江西省,江西省,江西省,辽宁省,辽宁省,辽宁省,辽宁省,辽宁省,辽宁省,辽宁省,"
				+"辽宁省,辽宁省,辽宁省,辽宁省,辽宁省,辽宁省,辽宁省,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,内蒙古自治区,宁夏回族自治区,宁夏回族自治区,宁夏回族自治区,宁夏回族自治区,宁夏回族自治区,青海省,青海省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山东省,山西省,山西省,山西省,山西省,山西省,山西省,山西省,山西省,"
				+"山西省,山西省,山西省,陕西省,陕西省,陕西省,陕西省,陕西省,陕西省,陕西省,陕西省,陕西省,陕西省,上海市,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,四川省,天津市,西藏自治区,西藏自治区,西藏自治区,西藏自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,"
				+"新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,新疆维吾尔自治区,云南省,云南省,云南省,云南省,云南省,云南省,云南省,云南省,云南省,云南省,云南省,云南省,云南省,浙江省,浙江省,浙江省,浙江省,浙江省,浙江省,浙江省,浙江省,浙江省,浙江省,浙江省,重庆市";
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
		JMenu menu0=new JMenu("注册");
		menuBar.add(menu0);
		JMenu menu1=new JMenu("查询");
		menuBar.add(menu1);
		JMenu menu2=new JMenu("退出");
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

 	
	public class Frameexit extends JFrame//退出类
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
			plabel.setText("是否真要退出？");
			plabel.setFont(new Font("",Font.BOLD,20));
			jpanel.add(plabel);

			final JButton button=new JButton();
			button.setText("是");
			button.setFont(new Font("",Font.BOLD,15));
			jpanel.add(button);
			final JButton sbutton=new JButton();
			sbutton.setText("否");
			sbutton.setFont(new Font("",Font.BOLD,15));
			jpanel.add(sbutton);
			
			button.addActionListener(new ActionListener()//强制退出
			{
				public void actionPerformed(ActionEvent e)
				{ 
					System.exit(0);
				}});
			
			sbutton.addActionListener(new ActionListener()//只关闭页面
			{
				public void actionPerformed(ActionEvent e)
				{ 
					dispose();
				}});
			
		}
		 
	}
	
	public class Frameperror extends JFrame//提示查询无前一项窗口
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
			plabel.setText("此项为最初项，\n无前一项，\n请核对后查询！");
			plabel.setFont(new Font("",Font.BOLD,15));
			jpanel.add(plabel);
		}
	}
	public class Framenerror extends JFrame//提示查询无后一项窗口
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
			plabel.setText("此项为最后项，无后一项，请核对后查询！");
			plabel.setFont(new Font("",Font.BOLD,15));
			jpanel.add(plabel);
		}
	}
	public class FrameBrowse extends JFrame//查询类
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
			toplabel.setText("   查 询 系 统   ");
			jpanel.add(toplabel);
			  final JLabel leftLabel=new JLabel(); 
			  leftLabel.setPreferredSize(new Dimension(10,0));
			  getContentPane().add(leftLabel,BorderLayout.NORTH);
//
			final JLabel label0=new JLabel();
			label0.setText("学号：");
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
			label1.setText("姓名：");
			jpanel.add(label1);
			label1.setFont(new Font("",Font.BOLD,30));
			JTextField textField1=new JTextField();
			textField1.setPreferredSize(new Dimension (220,35));
			textField1.setHorizontalAlignment(JTextField.CENTER);
			textField1.setFont(new Font("",Font.BOLD,30));
			jpanel.add(textField1);
//			
			final JLabel label=new JLabel();
			label.setText("性别：");
			label.setFont(new Font("",Font.BOLD,30));
			jpanel.add(label);
			ButtonGroup buttonGroup=new ButtonGroup();
			final JRadioButton manRadioButton=new JRadioButton();
			buttonGroup.add(manRadioButton);
			//manRadioButton.setSelected(true);
			manRadioButton.setText("男");
			manRadioButton.setFont(new Font("",Font.BOLD,30));
			jpanel.add(manRadioButton);
			final JRadioButton woRadioButton=new JRadioButton();
			buttonGroup.add(woRadioButton);
			woRadioButton.setSelected(true);
			woRadioButton.setText("女");
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
			provincelabel.setText("省份：");
			provincelabel.setFont(new Font("",Font.BOLD,30));
			jpanel.add(provincelabel);
			provinceBox.setEditable(true);
			provinceBox.setFont(new Font("",Font.BOLD,25));
			provinceBox.setPreferredSize(new Dimension (220,35));
			for(int i=0;i<protemp.size();i++)
			{
				provinceBox.addItem(protemp.get(i).province);
			}
			provinceBox.addActionListener(new ActionListener()//省市级联监听事件
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
			city_label.setText("城市：");
			city_label.setFont(new Font("",Font.BOLD,30));
			jpanel.add(city_label);
			cityBox.setEditable(true);
			cityBox.setFont(new Font("",Font.BOLD,25));
			cityBox.setPreferredSize(new Dimension (220,35));
			jpanel.add(cityBox);
			
//			
			JLabel checkboxlabel=new JLabel();
			checkboxlabel.setText("爱好：");
			checkboxlabel.setFont(new Font("",Font.BOLD,30));
			jpanel.add(checkboxlabel);
			final JCheckBox readingbox=new JCheckBox();
			final JCheckBox listenbox=new JCheckBox();
			final JCheckBox ballbox=new JCheckBox();
			final JCheckBox drawbox=new JCheckBox();
			final JCheckBox instrumentbox=new JCheckBox();
			final JCheckBox pcbox=new JCheckBox();
			final JCheckBox elsebox=new JCheckBox();
			readingbox.setText("读书");
			readingbox.setFont(new Font("",Font.BOLD,30));			
			listenbox.setText("听音乐");
			listenbox.setFont(new Font("",Font.BOLD,30));
			ballbox.setText("球类运动");
			ballbox.setFont(new Font("",Font.BOLD,30));
			drawbox.setText("绘画");
			drawbox.setFont(new Font("",Font.BOLD,30));
			instrumentbox.setText("乐器演奏");
			instrumentbox.setFont(new Font("",Font.BOLD,30));
			pcbox.setText("电子类");
			pcbox.setFont(new Font("",Font.BOLD,30));
			elsebox.setText("其他：");
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
			
			button.addActionListener(new ActionListener()//前一项事件
			{
				public void actionPerformed(ActionEvent e)
				{
					if(Lstep[0]==0)//设立flag同步记录防止越界
					{
						Frameperror wq=new Frameperror();
						wq.setVisible(true);
					}
					else
					{
					Lstep[0]--;
					if(Lstep[0]==0)//防止下一项回来第一项越界
					{
						Lstep[0]=1;
						Frameperror wq=new Frameperror();
						wq.setVisible(true);
					}
					else {
					HashMap<String,smallstudent> mapp=xxx.get(0);
					textField.setText(allstudents.get(Lstep[0]-1).sno);
					textField1.setText(mapp.get(allstudents.get(Lstep[0]-1).sno).sname);
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).sex.equals("男"))
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
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("读书"))
					{
						readingbox.setSelected(true);
					}
					if(allstudents.get(Lstep[0]-1).hobby.get(s).equals("听音乐"))
					{
						listenbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("球类运动"))
					{
						ballbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("绘画"))
					{
						drawbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("乐器演奏"))
					{
						instrumentbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("电子类"))
					{
						pcbox.setSelected(true);
					}
					}
					if(!(mapp.get(allstudents.get(Lstep[0]-1).sno).hobbyelse.equals("无")))
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

			sbutton.addActionListener(new ActionListener()//情况同button
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
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).sex.equals("男"))
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
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("读书"))
					{
						readingbox.setSelected(true);
					}
					if(allstudents.get(Lstep[0]-1).hobby.get(s).equals("听音乐"))
					{
						listenbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("球类运动"))
					{
						ballbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("绘画"))
					{
						drawbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("乐器演奏"))
					{
						instrumentbox.setSelected(true);
					}
					if(mapp.get(allstudents.get(Lstep[0]-1).sno).hobby.get(s).equals("电子类"))
					{
						pcbox.setSelected(true);
					}
					}
					if(!(mapp.get(allstudents.get(Lstep[0]-1).sno).hobbyelse.equals("无")))
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
//设置左右边距保持美观	
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
			toplabel.setText("    注 册 系 统   ");
			
			jpanel.add(toplabel);
			  final JLabel leftLabel=new JLabel(); 
			  leftLabel.setPreferredSize(new Dimension(10,0));
			  getContentPane().add(leftLabel,BorderLayout.NORTH);
			  //
				final JLabel label0=new JLabel();
				label0.setText("学号：");
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
				label1.setText("姓名：");
				jpanel.add(label1);
				label1.setFont(new Font("",Font.BOLD,30));
				JTextField textField1=new JTextField();
				textField1.setPreferredSize(new Dimension (220,35));
				textField1.setHorizontalAlignment(JTextField.CENTER);
				textField1.setFont(new Font("",Font.BOLD,30));
				jpanel.add(textField1);
//				
				final JLabel label=new JLabel();
				label.setText("性别：");
				label.setFont(new Font("",Font.BOLD,30));
				jpanel.add(label);
				ButtonGroup buttonGroup=new ButtonGroup();
				final JRadioButton manRadioButton=new JRadioButton();
				buttonGroup.add(manRadioButton);
				//manRadioButton.setSelected(true);
				manRadioButton.setText("男");
				manRadioButton.setFont(new Font("",Font.BOLD,30));
				jpanel.add(manRadioButton);
				final JRadioButton woRadioButton=new JRadioButton();
				buttonGroup.add(woRadioButton);
				woRadioButton.setSelected(true);
				woRadioButton.setText("女");
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
				provincelabel.setText("省份：");
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
				city_label.setText("城市：");
				city_label.setFont(new Font("",Font.BOLD,30));
				jpanel.add(city_label);
				cityBox.setEditable(true);
				cityBox.setFont(new Font("",Font.BOLD,25));
				cityBox.setPreferredSize(new Dimension (220,35));
				jpanel.add(cityBox);
				
//				
				JLabel checkboxlabel=new JLabel();
				checkboxlabel.setText("爱好：");
				checkboxlabel.setFont(new Font("",Font.BOLD,30));
				jpanel.add(checkboxlabel);
				final JCheckBox readingbox=new JCheckBox();
				final JCheckBox listenbox=new JCheckBox();
				final JCheckBox ballbox=new JCheckBox();
				final JCheckBox drawbox=new JCheckBox();
				final JCheckBox instrumentbox=new JCheckBox();
				final JCheckBox pcbox=new JCheckBox();
				final JCheckBox elsebox=new JCheckBox();
				readingbox.setText("读书");
				readingbox.setFont(new Font("",Font.BOLD,30));			
				listenbox.setText("听音乐");
				listenbox.setFont(new Font("",Font.BOLD,30));
				ballbox.setText("球类运动");
				ballbox.setFont(new Font("",Font.BOLD,30));
				drawbox.setText("绘画");
				drawbox.setFont(new Font("",Font.BOLD,30));
				instrumentbox.setText("乐器演奏");
				instrumentbox.setFont(new Font("",Font.BOLD,30));
				pcbox.setText("电子类");
				pcbox.setFont(new Font("",Font.BOLD,30));
				elsebox.setText("其他：");
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
			button.setText("新增");
			button.setFont(new Font("宋体",Font.BOLD,36));
			jpanel.add(button);
			//button.addActionListener();
			JButton a=new JButton("   ");
		    a.setContentAreaFilled(false);
		  	a.setBorderPainted(false);
			a.setEnabled(false);
			jpanel.add(a);
			final JButton sbutton=new JButton();
			sbutton.setText("保存");
			sbutton.setFont(new Font("宋体",Font.BOLD,36));
			jpanel.add(sbutton);
			
//
			button.addActionListener(new ActionListener()//存入allstudents列表监听
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
								demo.sex="女";
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
								demo.hobbyelse="无";
							}
							allstudents.add(demo);
							
							//System.out.print(demo.sno+demo.sname+demo.sex+demo.province+demo.city+demo.hobby.get(0)+demo.hobbyelse);
						}
						
					});
			sbutton.addActionListener(new ActionListener()//存入txt文件监听 
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
							out.write("学号："+demo.sno+"\n姓名："+demo.sname+"\n性别："+demo.sex+"\n省份："+demo.province+"\n城市："+demo.city+"\n爱好：");
							for(int i=0;i<demo.hobby.size();i++)
							{
								out.write(demo.hobby.get(i)+"\t");
							}
							out.write("\n其他："+demo.hobbyelse+"\n");
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
