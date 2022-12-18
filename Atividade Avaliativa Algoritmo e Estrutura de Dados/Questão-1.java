import java.util.Scanner;
import java.util.stream.IntStream;

//import avaliacao.No;

//import java.util.*;

//import avaliacao.No;


class agenda{
	
	

	public String Nome;
	public int[] Telefone;
	public String Email;
	public String Endereco;
	public String Rede_Social;
	private int Chave; //segurança
	
	
	//Na agenda o número de telefone é exclusivo para a pessoa
	//Portanto, o numero de telefone será a chave
	public String getNome() {
		return Nome;
	}
	
	public int getChave() {
		return Chave;
	}

	public void setChave(int chave) {
		Chave = chave;
	}
	
	public void setNome(String Nome) {
		this.Nome = Nome;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public int[] getTelefone() {
		return Telefone;
	}
	
	public void setTelefone(int[] Telefone) {
		this.Telefone = Telefone;
	}
	
	public String getEndereco() {
		return Endereco;
	}
	public void setEndereco(String Endereco) {
		this.Endereco = Endereco;
	}
	public String getRede_Social() {
		return Rede_Social;
	}
	public void setRede_Social(String Rede_Social) {
		this.Rede_Social = Rede_Social;
	}
//	public int criarChave(int[]Telefone) {
//		 return this.Chave = Telefone[1];
//		
//	}
	
	
	public String transformarTelefone_em_String() {
		return IntStream.of(Telefone).mapToObj(String::valueOf).reduce("", String::concat);
	}
	
	//teste= Listar_array_telefone(int[] Telefone);
	public String toString() {
		
			return " Nome: "+this.Nome+" Email: "+Email+" Endereço: "+Endereco+" Rede Social: "+this.Rede_Social+" Chave da agenda: "+this.Chave;
		}
	
	public agenda(String Nome,int[] Telefone,String Email, String Endereco,String Rede_Social, int Chave) {
		this.setNome(Nome);
		this.setEmail(Email);
		this.setEndereco(Endereco);
		this.setTelefone(Telefone);
		this.setChave(Chave);//se houver um erro é só apagar o get/set e mudar para public a chave.
		this.setRede_Social(Rede_Social);
	}

	
	
	public void Listar_array_telefone(Object telefone2) {
		// TODO Auto-generated method stub
		
	}

	
//	public void listarElementoAgenda() {
//		 System.out.print("\n Nome:"+Nome+"Endereço: "+Endereco+"Email: "+Email+"Rede Social: "+Rede_Social+"Telefone(s):"+Telefone);
//	}

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class No  {
	public int chave;
	public No direito;
	public No esquerdo;
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Arvore{
	private No raiz; //cria a raiz
	public Arvore() {raiz=null;}
	
	
	//---------------------------------*INSERIR*-------------------------------------------------------------------//
	public void inserir(int v) {
	    No novo = new No(); // cria um novo Nó
	    novo.chave = v; // atribui o valor recebido ao item de dados do Nó
	    novo.direito = null;
	    novo.esquerdo = null;

	    if (raiz == null) raiz = novo;
	    else  { // se nao for a raiz
	      No atual = raiz;
	      No anterior;
	      while(true) {
	        anterior = atual;
	        if (v <= atual.chave) { // ir para esquerda
	          atual = atual.esquerdo;
	          if (atual == null) {
	            anterior.esquerdo = novo;
	            return;
	          } 
	        }  // fim da condição ir a esquerda
	        else { // ir para direita
	           atual = atual.direito;
	           if (atual == null) {
	             anterior.direito = novo;
	             return;
	           }
	        } // fim da condição ir a direita
	      } // fim do laço while
	    } // fim do else não raiz

	  }
	
	
	
	//----------------------*REMOVER*----------------------------------------------------------//
	public boolean remover(int item_remover) {
	    if (raiz == null) return false; // se arvore vazia

	    No atual = raiz;
	    No pai = raiz;
	    boolean filho_esq = true;

	    // ****** Buscando o valor **********
	    while (atual.chave != item_remover) { // enquanto nao encontrou
	      pai = atual;
	      if(item_remover < atual.chave ) { // caminha para esquerda
	        atual = atual.esquerdo;
	        filho_esq = true; // é filho a esquerda? sim
	      }
	      else { // caminha para direita
	        atual = atual.direito; 
	        filho_esq = false; // é filho a esquerda? NAO
	      }
	      if (atual == null) return false; // encontrou uma folha -> sai
	    } // fim laço while de busca do valor

	    // **************************************************************
	    // se chegou aqui quer dizer que encontrou o valor (v)
	    // "atual": contem a referencia ao No a ser eliminado
	    // "pai": contem a referencia para o pai do No a ser eliminado
	    // "filho_esq": é verdadeiro se atual é filho a esquerda do pai
	    // **************************************************************

	    // Se nao possui nenhum filho (é uma folha), elimine-o
	    if (atual.esquerdo == null && atual.direito == null) {
	      if (atual == raiz ) raiz = null; // se raiz
	      else if (filho_esq) pai.esquerdo = null; // se for filho a esquerda do pai
	           else pai.direito = null; // se for filho a direita do pai
	    }

	    // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
	    else if (atual.direito == null) {
	       if (atual == raiz) raiz = atual.esquerdo; // se raiz
	       else if (filho_esq) pai.esquerdo = atual.esquerdo; // se for filho a esquerda do pai
	            else pai.direito = atual.esquerdo; // se for filho a direita do pai
	    }
	    
	    // Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
	    else if (atual.esquerdo == null) {
	       if (atual == raiz) raiz = atual.direito; // se raiz
	       else if (filho_esq) pai.esquerdo = atual.direito; // se for filho a esquerda do pai
	            else pai.direito = atual.direito; // se for  filho a direita do pai
	    }

	    // Se possui mais de um filho, se for um avô ou outro grau maior de parentesco
	    else {
	      No sucessor = no_sucessor(atual);
	      // Usando sucessor que seria o Nó mais a esquerda da subarvore a direita do No que deseja-se remover
	      if (atual == raiz) raiz = sucessor; // se raiz
	      else if(filho_esq) pai.esquerdo = sucessor; // se for filho a esquerda do pai
	           else pai.direito = sucessor; // se for filho a direita do pai
	      sucessor.esquerdo = atual.esquerdo; // acertando o ponteiro a esquerda do sucessor agora que ele assumiu 
	                                // a posição correta na arvore   
	    }

	    return true;
	  }
	  
	  // O sucessor é o Nó mais a esquerda da subarvore a direita do No que foi passado como parametro do metodo
	  public No no_sucessor(No apaga) { // O parametro é a referencia para o No que deseja-se apagar
	     No paidosucessor = apaga;
	     No sucessor = apaga;
	     No atual = apaga.direito; // vai para a subarvore a direita

	     while (atual != null) { // enquanto nao chegar no Nó mais a esquerda
	       paidosucessor = sucessor;
	       sucessor = atual;
	       atual = atual.esquerdo; // caminha para a esquerda
	     } 
	     // *********************************************************************************
	     // quando sair do while "sucessor" será o Nó mais a esquerda da subarvore a direita
	     // "paidosucessor" será o o pai de sucessor e "apaga" o Nó que deverá ser eliminado
	     // *********************************************************************************
	     if (sucessor != apaga.direito) { // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
	       paidosucessor.esquerdo = sucessor.direito; // pai herda os filhos do sucessor que sempre serão a direita
	       // lembrando que o sucessor nunca poderá ter filhos a esquerda, pois, ele sempre será o
	       // Nó mais a esquerda da subarvore a direita do Nó apaga.
	       // lembrando também que sucessor sempre será o filho a esquerda do pai

	       sucessor.direito = apaga.direito; // guardando a referencia a direita do sucessor para 
	                                 // quando ele assumir a posição correta na arvore
	     }
	     return sucessor;
	  }
	  
	  
	//------------------------------------*BUSCAR*------------------------------------------------------  
	  public No buscar(long chave) {
		    if (raiz == null) return null; // se arvore vazia
		    No atual = raiz;  // começa a procurar desde raiz
		    while (atual.chave != chave) { // enquanto nao encontrou
		      if(chave < atual.chave ) atual = atual.esquerdo; // caminha para esquerda
		      else atual = atual.direito; // caminha para direita
		      if (atual == null) return null; // encontrou uma folha -> sai
		    } // fim laço while
		    return atual; // terminou o laço while e chegou aqui é pq encontrou item
		  }
	  
	  
	 
//	  public boolean equals(Object obj) {  
//		     if (this == obj)  // se as referências forem iguais, é true
//		             return true;  
//		     if (obj == null)  // this é sempre diferente de null - esse cheque é feito para evitar um NullPointerException abaixo
//		             return false;  
//		     if (getClass() != obj.getClass())  // se não forem exatamente da mesma classe, são diferentes
//		             return false;  
//		     agenda other = (agenda) obj;  // fazemos o cast
//		     if (getIdIdioma() == null) {  // comparamos por idIdioma. Se ambos forem null, então são pela sua convenção iguais.
//		             if (other.getIdIdioma() != null)  
//		                     return false;  
//		     } else if (!idIdioma.equals(other.idIdioma))  // comparamos por idIdioma, usando o equals de idIdioma
//		             return false;  
//		   
//		   
//		     return true;  
//		 } 
//	  
	  
	  
	  public void inOrder(No atual) {
		    if (atual != null) {
		      inOrder(atual.esquerdo);
		      System.out.print(atual.chave + " ");
		      inOrder(atual.direito);
		    }
		  }
		  
		  public void preOrder(No atual) {
		    if (atual != null) {
		      System.out.print(atual.chave + " ");
		      preOrder(atual.esquerdo);
		      preOrder(atual.direito);
		    }
		  }
		  
//		  public void posOrder(No atual) {
//		    if (atual != null) {
//		      posOrder(atual.esquerdo);
//		      posOrder(atual.direito);
//		      System.out.print(atual.esquerdo + " ");
//		    }
//		  }  
		  
		  
		  
		  public void caminhar() {
			    System.out.print("\n Exibindo em ordem: ");
			    inOrder(raiz);
//			    System.out.print("\n Exibindo em pos-ordem: ");
//			    posOrder(raiz);
			    System.out.print("\n Exibindo em pre-ordem: ");
			    preOrder(raiz);
			    System.out.print("\n Altura da arvore: " + altura(raiz));
			    //System.out.print("\n Quantidade de folhas: " + folhas(raiz));
			    //System.out.print("\n Quantidade de Nós: " + contarNos(raiz));
			    if (raiz != null ) {  // se arvore nao esta vazia
			       System.out.print("\n Valor minimo: " + min().chave);
			       System.out.println("\n Valor maximo: " + max().chave);
			    }
			  }
		  
		  public int altura(No atual) {

			     if(atual == null || (atual.esquerdo == null && atual.direito == null))
			       return 0;
			     else {
			   	if (altura(atual.esquerdo) > altura(atual.direito))
			   	   return ( 1 + altura(atual.esquerdo) );
			   	else
				   return ( 1 + altura(atual.direito) );
			     }
			  }
		  
		  
		  
		 
		  
		  public No min() {
			    No atual = raiz;
			    No anterior = null;
			    while (atual != null) {
			      anterior = atual;
			      atual = atual.esquerdo;
			    }
			    return anterior;
			  }

			  public No max() {
			    No atual = raiz;
			    No anterior = null;
			    while (atual != null) {
			      anterior = atual;
			      atual = atual.direito;
			    }
			    return anterior;
			  }

	
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Agendaapp{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Arvore arv = new Arvore();
		//////////////////*Declaração das variáveis*/////////////////////
		int[]Telefone = new int[11];
		String Nome;
		String Email;
		String Endereco;
		String Rede_Social;
		int opcao;
		int chave;
		int x;
		int y;
		No vi = null;
		
		//=========================================*Telefone-exibição-instância*===================================================================//
		int incrementar_telefone;
		String[] guardar_classe_exibicao = new String[200];//Para guardar os elementos para posterior exibição
		int contador_array_exibicao = 0;
		int[] guardar_chaves = new int[200];
		//int array_indice_telefone = 0;
		//int[] guardar_telefones_elemento = new int[11];
		//int[] guardar_telefones_array = new int[200];
		//int contador_guardar_telefones_elemento = 0;
		
		///////////////////////////////////////////
		
		////////////////////////////*interface de opções*//////////////////////////////////////////////////////////////////////////////////
		do {
			System.out.println("\nNesta agenda, o método de busca é pelo Número que você cadastrou como chave");
	        System.out.print("\n***********************************");
	        System.out.print("\nEntre com a opcao:");
		System.out.print("\n ----1: Inserir agenda");
		System.out.print("\n ----2: Excluir");
		System.out.print("\n ----3: Alterar");
		//System.out.print("\n ----5: Pesquisar");
		System.out.print("\n ----4: Exibir");
		System.out.print("\n ----5: Pesquisar");
		System.out.print("\n ----6: Sair do programa");
		//System.out.print("\n ----5: Alterar");
		System.out.print("\n***********************************");
		System.out.print("\n-> ");
		opcao = sc.nextInt();
		//int contador = 1;//para evitar que a mesma chave seja adicionada em duas classes
		
		
		switch(opcao) {
		
		
		//==================================================*INSERIR*==============================================================//
	 	case 1: {
		      
	 		
	 		//É preciso criar a agenda antes de tudo
	 		//Criação da chave: 
	 		System.out.println("\n Vamos adicionar um número que funcionará como chave.");
	 		System.out.println("OBS: Um número de telefone possui 9 números");
	 		System.out.println("Digite o número de telefone principal:");
	 		chave = sc.nextInt();
	 		
	 			do {
	 				System.out.println("Caso você deseja adicionar mais um telefone digite 1");
	 				System.out.println("OBS: Um número de telefone possui 9 números");
	 				System.out.println("Caso deseje encerrar o programa, digite 2");
	 				incrementar_telefone = sc.nextInt();
	 			
	 						switch(incrementar_telefone) {
	 						//===========================*Adicionar outros telefones*=====================================//
	 					 
	 						case 1: {
	 						
	 							int array_indice_telefone=0;
	 						
	 						
	 							System.out.println("A agenda está limitada a 10 números:");//decidi limitar o tamanho do array
	 							System.out.println("Caso tenha concluido a inserção de telefones, digite 2: ");
	 							
	 						
	 								Telefone[array_indice_telefone] = sc.nextInt();//adicionando o telefone ao array
//	 								guardar_telefones_elemento[contador_guardar_telefones_elemento]=Telefone[array_indice_telefone];
//	 								contador_guardar_telefones_elemento++;
 								array_indice_telefone++;
	 								break;
	 								
	 							}
	 					
	 						//===========================*encerrar a inserção da agenda====================================//
	 						case 2:{
	 							break;
	 								}
	 						
	 							}
	 				}while(incrementar_telefone!=2);
	 		
	 		
	 		if(Telefone.length==0) { //Caso não houer nenhum telefone
	 			Telefone[0] = chave; //o campo de telefone é igual a chave
	 		}
	 		System.out.println("Insira um nome: ");
	 		Nome = sc.next();
	 		System.out.println("Insira um Email");
	 		Email = sc.next();
	 		System.out.println("Insira um endereço");
	 		Endereco = sc.next();
	 		System.out.println("Insira uma rede social(twitter,Instagram,linkedin,etc) :");
	 		Rede_Social = sc.next();
	 		
	 		
	 		
	 		agenda classe_chave = new agenda(Nome,Telefone,Email,Endereco,Rede_Social,chave);//Eu criei a classe para  o caso do programador querer consultar depois
	 		classe_chave.Telefone = Telefone;
	 		System.out.println("Criada a agenda"+classe_chave);//debug
	 		System.out.print(classe_chave.getClass());//DEBUG
	 		//classe_chave.Listar_array_telefone(int[] Telefone);
	 		
	 		//
	 		//guardar_classe_exibicao[contador_array_exibicao ] = {" Nome: "+Nome+" Telefone: "+telefone_string+" Email: "+" Endereço: "+Endereco+" Rede Social: "+Rede_Social+" Chave: "+Chave};
	 		guardar_classe_exibicao[contador_array_exibicao ] = classe_chave.toString();
	 		guardar_chaves[contador_array_exibicao ] = chave;
	 		//System.out.println(classe_chave.toString());
	 		// String resposta = IntStream.of(Telefone).mapToObj(String::valueOf).reduce("", String::concat);
	 			
	 		   
		       
		       arv.inserir(chave);
		       //contador ++;
		       contador_array_exibicao ++;
		       break;
		}
	 		
		
	 	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 	
		
		
		//=======================================================*REMOVER*====================================================//
		case 2: {
		       System.out.print("\n Informe o valor numero -> ");
		       x = sc.nextInt();
		       if ( !arv.remover(x) )
                          System.out.print("\n Número nao encontrado!");;
		       break;
		   
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		//===================================================*ALTERAR*========================================================//
		case 3: {
			System.out.println("Informe o Número existente que você deseja alterar(int) ->");
				x = sc.nextInt();
			System.out.println("Digite O novo");
			    y = sc.nextInt();
			   
			if( arv.buscar(x) != null  )
				vi = arv.buscar(x) ;
			    vi.chave=y;
			
				break;
				
			
		}
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//==================================================*BUSCAR*=========================================================//
		case 5: {
			int i;
			
		       System.out.print("\n Informe o valor (long) -> ");
                       x = sc.nextInt();
	      	       if( arv.buscar(x) != null ) {
	      	    	   for(i=0;i<=guardar_chaves.length;i++) {
	      	    		   if(guardar_chaves[i]==x) {
	      	    			  
	      	    			 System.out.print("\n Valor Encontrado!");
	      	    			 System.out.println("\n"+guardar_classe_exibicao[i]);
	      	    			 	break;
	      	    		   }
	      	    	   }}

	      	     else
		          System.out.print("\n Valor nao encontrado!");
		       break;
		}	
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//===============================*CAMINHAR*================================================================================//
		case 4: {
		      arv.caminhar();
		      break; 
		}
		
		
        }} while(opcao != 6);
	    sc.close();
		
	}}
		
		
			
		
		
		


