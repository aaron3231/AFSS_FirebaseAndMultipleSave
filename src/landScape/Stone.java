package landScape;

public class Stone extends Decoration {

		private int category;//���ۺ���
		
		//�غc��
		public Stone(int category) 
		{
			super();
			this.setPrice(10);
			//�bfunction���@�ֳ]�w �]�ƦW�� �j�p �]�Ʊԭz
			this.setCategory(category);
		}
		
		//set function
		public void setCategory(int category) 
		{
			this.category = category;
			//�Z�Z��
			if(this.category == COBBLE)
			{
				this.setName("�Z�Z��");
				this.setStatement("�Z�Z�۾A�X�Φb�˹��p�����h�����ڽc");
				//�]�w�Z�Z�۪�������j�p
				//�j�p 1x1
				this.setSizeX(1);
				this.setSizeY(1);
			}
			//�W����
			else if(this.category == PRISM)
			{
				this.setName("�W����");
				this.setStatement("�W���ۥi�x�Ω�j�h�ƪ�������");
				//�]�w�W���۪�������j�p
				//�j�p 1x2
				this.setSizeX(1);
				this.setSizeY(2);
			}
		}
		
		//get function
		public int getCategory() 
		{
			return this.category;
		}
		
		@Override
		public String toString() 
		{
			String forword = super.toString();
			String str;
			str =  String.format("���Y�����G%s %n", this.category == COBBLE?"�Z�Z��":"�W����");
			return forword + str;
		}
}
