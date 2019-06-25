package enterPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import imagePanel.ImagePanel;

@SuppressWarnings("serial")
public class EnterPanel extends ImagePanel
{
	//�������s
	private JButton description = new JButton();
    //���ʨƥ�
    private ButtonHandler bH = new ButtonHandler();
    
    public EnterPanel(Image im)
    {	
    	super(im);
    	//�]�w�������s
    	setDescription();
    }
    
    public EnterPanel(String im)
    {
    	super(im);
    	setDescription();
    }
    
    private void setDescription()
    {
    	//�������s
    	description.setText("����");
        description.setFont(new Font(null, Font.BOLD, 15));
        description.setBounds(550, 560, 100, 50);
        add(description);
        //�[�J�\��
        description.addActionListener(bH);
    }
    
    private class ButtonHandler implements  ActionListener
  	{
  		public void actionPerformed(ActionEvent ae) 
        {
  			JTextArea descriptionT = new JTextArea();
  			JScrollPane descriptionS = new JScrollPane(descriptionT);
  			JDialog descriptionD = new JDialog();
  			
  			System.out.println("test1");
  			descriptionT.setText("AFSS�Ѹ���֭p�B�\��]�p�B�[�t�����B���A�d�ߡB�֭p�Ϫ��Ҳղզ��C\n"
  					+ "�q���ʸ귽�}�l�A�b�}�i���C�Ӷ��q���i�H���ϥΪ̩��T���D�w�g��J������C\n"
  					+ "�z�L�ۥѪ��\��]�p�|�v�T���ڽc���ҲӸ`�A�i�Ӳ��ͽ��������C\n"
  					+ "�[�t�������ɶ�����k���i�o��֡A�ݨ��Y�u�ӶO���ɪ������ĪG�A\n"
  					+ "�i�H�z�L�Ŀ���L�S�w�ƥ�i��[�t�C\n"
  					+ "�]���C�Ӯɶ��I���b�i������åB�N������ƫئ��@�Ӹ�Ʈw�A\n"
  					+ "�ҥH�i�J���A�d�ߥi�H�d�ݨ��U���������ҿ��A\n"
  					+ "�������ҿ�椺�]�t�������A�B�����]�ƪ��A�B�����\�]�]�w�B\n"
  					+ "���v��Ƭd�ߡB�ƥ����ï�A�C���i�J��泣�|��Timer�Ȱ��A\n"
  					+ "�b������檺�ɭ��x�s��U���ܪ����A�A\n"
  					+ "���v��Ƭd�ߥΧ�u�Ϫ�ܥi�H���ϥΪ��[�ݹL�h�S�w�ɶ��������C\n"
  					+ "����\��ݨ�h�u�X�@�A�i�H���ϥΪ̰��ƥ�����B�������������A\n"
  					+ "��K���^�U�ΰ������[��C���צۥѤƪ��F���]�w�A\n"
  					+ "�i�H�b���N�ɶ������\�]�άO�]�ƪ��A�C\n"
  					+ "���G���|ø�s����u�����ϥΪ̤@���A�M�ֳt�F�Ѿ��鱡�ΡC");
  			descriptionT.setFont(new Font(null, Font.BOLD, 20));
  	        descriptionT.setBackground(Color.white);
  	        descriptionT.setEditable(false);
  	        descriptionT.setCaretPosition(0);
  			
  			descriptionS.setBounds(25, 20, 630, 520);
  			descriptionD.add(descriptionS);
  			
  			descriptionD.setTitle("����");
  			descriptionD.setAlwaysOnTop(true);
  			System.out.println("test2");
  			descriptionD.setModal(true);
  			descriptionD.setLayout(null);
  			descriptionD.setBounds(300, 200, 700, 600);
  		    descriptionD.setVisible(true);/////////////////////////���s
  		    System.out.println("test3");
  		    descriptionD.dispose();
  		    System.out.println("test4");
        }
  	}
}