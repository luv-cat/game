import java.util.*;
import java.util.stream.*;
//�r���S�̃N���X
public class Bingo{
	public static void main(String... args){
		Scanner sc=new Scanner(System.in);
		Random random=new Random();
		BingoMatrix bingo=new BingoMatrix();
		//�ŏ��Ƀr���S�J�[�h��\������
		System.out.println(bingo);
		while(bingo.reachNum()>=0){
			System.out.println("���[�`���F"+bingo.reachNum());
			String _1=sc.nextLine();
			int x=random.nextInt(75)+1;
			System.out.println("�o�������F"+x);
			if(bingo.call(x)){
				System.out.println("Hit");
			}else{
				System.out.println("Deviate");
			}
			System.out.println(bingo);
		}
		System.out.println("congratulation!!!");
	}
	
	//�r���S�J�[�h��\���Ă���
	public final static class BingoMatrix{
		/*
		�r���S�J�[�h�̂��ꂼ��̖ڂ̐���\���B
		�������A-1�̂Ƃ��͂��łɌ����J���Ă���Ƃ���B
		*/
		int[][] matrix=new int[5][5];
		final String separator="----------------";
		BingoMatrix(){
			init();
		}
		/*
		�r���S�J�[�h�̖ڂ�����������B
		*/
		private void init(){
			for(int i=0;i<5;i++){
				//[1,15],[16,30]...�̒�������Ȃ��悤�Ƀ����_���Ŗڂ����߂�
				//���̂��߂�[1,15],[16,30]...�̃��X�g��p�ӂ��V���b�t��������A�O����5�I�ԁB
				List<Integer> list=IntStream.rangeClosed(1+i*15,(i+1)*15).boxed().collect(Collectors.toList());
				Collections.shuffle(list);
				for(int j=0;j<5;j++){
					matrix[j][i]=list.get(j);
				}
			}
			//�^�񒆂͍ŏ�����󂢂Ă��邱�Ƃ�Y�ꂸ��
			matrix[2][2]=-1;
		}
		/*
		���鐔���R�[���i�ǂ��\�����邩�킩��Ȃ��̂ł����ł̓R�[���ƌĂԁj���ꂽ�Ƃ��A
		���̒l���J�[�h�ɗL��Ȃ�Ό���������true��Ԃ��B
		�Ȃ��Ȃ��false��Ԃ��B
		*/
		public boolean call(int x){
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					if(matrix[i][j]==x){
						matrix[i][j]=-1;
						return true;
					}
				}
			}
			return false;
		}
		/*
		���[�`�̐���Ԃ�
		�������r���S���������Ă���Ƃ���-1��Ԃ��B
		if(num==4)...if(num==5)...�̂Ƃ��낪������Ƃ��ǂ��̂ł��܂����Ƃ������G
		*/
		public int reachNum(){
			int result=0;
			for(int i=0;i<5;i++){
				int num=0;
				for(int j=0;j<5;j++){
					if(matrix[i][j]==-1)num++;
				}
				if(num==4) result++;
				if(num==5) return -1;
			}
			for(int j=0;j<5;j++){
				int num=0;
				for(int i=0;i<5;i++){
					if(matrix[i][j]==-1)num++;
				}
				if(num==4)result++;
				if(num==5) return -1;
			}
			int num=0;
			for(int i=0;i<5;i++){
				if(matrix[i][i]==-1)num++;
			}
			if(num==4)result++;
			if(num==5) return -1;
			num=0;
			for(int i=0;i<5;i++){
				if(matrix[i][4-i]==-1)num++;;
			}
			if(num==4)result++;
			if(num==5) return -1;
			return result;
			//naname
		}
		/*
		
		B,I,N,G,O��S�p�ɂ��Ă��邱�Ƃɒ���
		*/
		@Override
		public String toString(){
			StringBuilder sb=new StringBuilder();
			sb.append(separator+"\n");
			sb.append("|�a|�h|�m|�f|�n|\n");
			sb.append(separator+"\n");
			for(int i=0;i<5;i++){
				sb.append("|"+Arrays.stream(matrix[i]).mapToObj(j->{
					if(j==-1){
						return "��";
					}else{
						return (j<10?" ":"")+j;
					}
					
				}).collect(Collectors.joining("|"))+"|\n");
				sb.append(separator+"\n");
			}
			return sb.toString();
		}
		
	}
}
