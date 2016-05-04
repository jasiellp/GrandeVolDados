package teste;

import com.processo.leao.verde.util.DBLeao;

public class main_
{
	
	public static void main(String[] args)
	{
	
				
				StringBuffer sb = new StringBuffer();
				
				sb.append(" INSERT INTO ").append(" \n"); 
				sb.append(" `configure_frame`  ").append(" \n");
				sb.append(" (`id_config`, `nome`, `categoria`, `url`, `status`) ").append(" \n"); 
				sb.append(" VALUES (0, 'Raquel', 'Lindo', 'http:\\\\orkut.com', 1); ").append(" \n");

				System.out.println("Query :".concat(sb.toString()));

				try
				{
					DBLeao.Insert(sb);
				}
				catch (Exception e)
				{
						System.out.println(e.getMessage());
					e.printStackTrace();
				}
				 
			}
}
