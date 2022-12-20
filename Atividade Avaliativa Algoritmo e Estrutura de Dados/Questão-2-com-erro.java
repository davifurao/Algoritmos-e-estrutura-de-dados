import java.util.Random;
import java.util.Scanner;

class No {
  public long item;
  public No dir;
  public No esq;
}

//=======================================================================================================================================//
////////////////////////////////////////////////
class Tree {
  private No root; // raiz

  public Tree() { root=null; } // inicializa arvore

  public void inserir(long v) {
    No novo = new No(); // cria um novo Nó
    novo.item = v; // atribui o valor recebido ao item de dados do Nó
    novo.dir = null;
    novo.esq = null;

    if (root == null) root = novo;
    else  { // se nao for a raiz
      No atual = root;
      No anterior;
      while(true) {
        anterior = atual;
        if (v <= atual.item) { // ir para esquerda
          atual = atual.esq;
          if (atual == null) {
            anterior.esq = novo;
            return;
          } 
        }  // fim da condição ir a esquerda
        else { // ir para direita
           atual = atual.dir;
           if (atual == null) {
             anterior.dir = novo;
             return;
           }
        } // fim da condição ir a direita
      } // fim do laço while
    } // fim do else não raiz

  }

  public No buscar(long chave) {
    if (root == null) return null; // se arvore vazia
    No atual = root;  // começa a procurar desde raiz
    while (atual.item != chave) { // enquanto nao encontrou
      if(chave < atual.item ) atual = atual.esq; // caminha para esquerda
      else atual = atual.dir; // caminha para direita
      if (atual == null) return null; // encontrou uma folha -> sai
    } // fim laço while
    return atual; // terminou o laço while e chegou aqui é pq encontrou item
  }

  
  public No buscar_e_adicionar_no_array(long chave,long[] passada_caminho) {//O array de comparação vai ser passado como parâmetro
	  if(root ==null) return null;
	  int contador =0;
	  No atual = root;
	  //long[] passada_caminho = new long[tamanho_do_array];
	  passada_caminho[0]=atual.item;
	  while(atual.item !=chave) {
		  if(chave < atual.item ) {
			  atual = atual.esq;
			  passada_caminho[contador]=atual.item;
			  contador++;
		  }
		  else {
			  atual = atual.dir;
			  passada_caminho[contador] = atual.item;
			  contador++;
		  }
	  }
	  
	  return atual ;//&& passada_caminho;
  }


  public boolean remover(long v) {
    if (root == null) return false; // se arvore vazia

    No atual = root;
    No pai = root;
    boolean filho_esq = true;

    // ****** Buscando o valor **********
    while (atual.item != v) { // enquanto nao encontrou
      pai = atual;
      if(v < atual.item ) { // caminha para esquerda
        atual = atual.esq;
        filho_esq = true; // é filho a esquerda? sim
      }
      else { // caminha para direita
        atual = atual.dir; 
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
    if (atual.esq == null && atual.dir == null) {
      if (atual == root ) root = null; // se raiz
      else if (filho_esq) pai.esq = null; // se for filho a esquerda do pai
           else pai.dir = null; // se for filho a direita do pai
    }

    // Se é pai e nao possui um filho a direita, substitui pela subarvore a direita
    else if (atual.dir == null) {
       if (atual == root) root = atual.esq; // se raiz
       else if (filho_esq) pai.esq = atual.esq; // se for filho a esquerda do pai
            else pai.dir = atual.esq; // se for filho a direita do pai
    }
    
    // Se é pai e nao possui um filho a esquerda, substitui pela subarvore a esquerda
    else if (atual.esq == null) {
       if (atual == root) root = atual.dir; // se raiz
       else if (filho_esq) pai.esq = atual.dir; // se for filho a esquerda do pai
            else pai.dir = atual.dir; // se for  filho a direita do pai
    }

    // Se possui mais de um filho, se for um avô ou outro grau maior de parentesco
    else {
      No sucessor = no_sucessor(atual);
      // Usando sucessor que seria o Nó mais a esquerda da subarvore a direita do No que deseja-se remover
      if (atual == root) root = sucessor; // se raiz
      else if(filho_esq) pai.esq = sucessor; // se for filho a esquerda do pai
           else pai.dir = sucessor; // se for filho a direita do pai
      sucessor.esq = atual.esq; // acertando o ponteiro a esquerda do sucessor agora que ele assumiu 
                                // a posição correta na arvore   
    }

    return true;
  }
  
  // O sucessor é o Nó mais a esquerda da subarvore a direita do No que foi passado como parametro do metodo
  public No no_sucessor(No apaga) { // O parametro é a referencia para o No que deseja-se apagar
     No paidosucessor = apaga;
     No sucessor = apaga;
     No atual = apaga.dir; // vai para a subarvore a direita

     while (atual != null) { // enquanto nao chegar no Nó mais a esquerda
       paidosucessor = sucessor;
       sucessor = atual;
       atual = atual.esq; // caminha para a esquerda
     } 
     // *********************************************************************************
     // quando sair do while "sucessor" será o Nó mais a esquerda da subarvore a direita
     // "paidosucessor" será o o pai de sucessor e "apaga" o Nó que deverá ser eliminado
     // *********************************************************************************
     if (sucessor != apaga.dir) { // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
       paidosucessor.esq = sucessor.dir; // pai herda os filhos do sucessor que sempre serão a direita
       // lembrando que o sucessor nunca poderá ter filhos a esquerda, pois, ele sempre será o
       // Nó mais a esquerda da subarvore a direita do Nó apaga.
       // lembrando também que sucessor sempre será o filho a esquerda do pai

       sucessor.dir = apaga.dir; // guardando a referencia a direita do sucessor para 
                                 // quando ele assumir a posição correta na arvore
     }
     return sucessor;
  }
  
  public void caminhar() {
    System.out.print("\n Exibindo em ordem: ");
    inOrder(root);
    System.out.print("\n Exibindo em pos-ordem: ");
    posOrder(root);
    System.out.print("\n Exibindo em pre-ordem: ");
    preOrder(root);
    System.out.print("\n Altura da arvore: " + altura(root));
    System.out.print("\n Quantidade de folhas: " + folhas(root));
    System.out.print("\n Quantidade de Nós: " + contarNos(root));
    if (root != null ) {  // se arvore nao esta vazia
       System.out.print("\n Valor minimo: " + min().item);
       System.out.println("\n Valor maximo: " + max().item);
    }
  }

  public void inOrder(No atual) {
    if (atual != null) {
      inOrder(atual.esq);
      System.out.print(atual.item + " ");
      inOrder(atual.dir);
    }
  }
  
  public void preOrder(No atual) {
    if (atual != null) {
      System.out.print(atual.item + " ");
      preOrder(atual.esq);
      preOrder(atual.dir);
    }
  }
  
  public void posOrder(No atual) {
    if (atual != null) {
      posOrder(atual.esq);
      posOrder(atual.dir);
      System.out.print(atual.item + " ");
    }
  }  
  
  public int altura(No atual) {
     if(atual == null || (atual.esq == null && atual.dir == null))
       return 0;
     else {
   	if (altura(atual.esq) > altura(atual.dir))
   	   return ( 1 + altura(atual.esq) );
   	else
	   return ( 1 + altura(atual.dir) );
     }
  }
  
  
 
  
  public int folhas(No atual) {
    if(atual == null) return 0;
    if(atual.esq == null && atual.dir == null) return 1;
    return folhas(atual.esq) + folhas(atual.dir);
  }
  
  public int contarNos(No atual) {
   if(atual == null)  return 0;
   else return ( 1 + contarNos(atual.esq) + contarNos(atual.dir));
  }

  public No min() {
    No atual = root;
    No anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.esq;
    }
    return anterior;
  }

  public No max() {
    No atual = root;
    No anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.dir;
    }
    return anterior;
  }

}
//=======================================================================================================================================//
////////////////////////////////////////////////

class FormigaAntApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Tree arv = new Tree();
		int opcao;
		int qntd_no = 0;
		int indice_passada = 0;
		long caminhar;
		int[] array_comparacao = new int[qntd_no];
		long[] passada_caminho = new long[qntd_no];//iniciar a variável Para comparação de array comparação
		//int[]  array_arvore = new int[qntd_no];
		long Soma_verificacao = 0;
		long Soma_verificacao_passada = 0;
		
		
		System.out.print("\nJogo da Formiguinha ");
		
		//do {
			int i;//para ser usado nos laços
			
			
			System.out.println("Bem vido ao Walk Ant Game!!");
			System.out.println("Vamos primeiro construir o Caminho");
			System.out.println("\nDetermine a quantidade de nós");
			qntd_no = sc.nextInt();
			System.out.println("\n\n\nCriando caminho...");
			int[]  array_arvore = new int[qntd_no];
	        Random gerador = new Random();
	        
 
			for(i=0;i<qntd_no;i++) {
				int valor=gerador.nextInt(40);
				array_arvore[i]=valor;
				arv.inserir(valor); //inserindo o número aleatório na arvore
				
			}
			System.out.println("\nCaminho Criado!!!");
			
		//===============================*Mostra o caminho criado*====================================================================//	
			System.out.println("\nCaminho: ");
			for(i=0;i<array_arvore.length;i++) {
				System.out.println(array_arvore[i]);
				Soma_verificacao=Soma_verificacao+array_arvore[i];
			}
			
			
			
			
//			System.out.println("\nAgora digite para aonde você quer ir: ");
			//int finalizar_caminhada=0;
//			caminhar = sc.nextInt();
			do {
//				for(i=0;i<qntd_no;i++) {
//					System.out.println(passada_caminho[i]);
//				}
				System.out.println("\n Digite para aonde você quer ir: ");
				caminhar = sc.nextLong();
				
				arv.buscar(caminhar);
				arv.caminhar();


//				for(i=0;i<qntd_no;i++) {
//					System.out.println(passada_caminho[i]);
//				}//debug
				
//				System.out.println(passada_caminho);
//				arv.buscar_e_adicionar_no_array(caminhar,passada_caminho);
//				
//				System.out.println("A formiga caminhou pelos nós: ");
//				for(i=0;i<passada_caminho.length;i++) {
//				System.err.println(passada_caminho[i]);
				
				//opcao=3;
//				//finalizar_caminhada=1;
//				}for(i=0;i<passada_caminho.length;i++) {
//					Soma_verificacao_passada=Soma_verificacao_passada+passada_caminho[i];
//				}
				
				
				
			}while(Soma_verificacao_passada ==Soma_verificacao);
			//opcao=3;
			
		//}while(opcao!=3);
	}
}





















//class ArvoreBinariaApp {
//	public static void main(String[] args) {
//	    Scanner le = new Scanner(System.in);
//	    Tree arv = new Tree();
//	    int opcao;
//	    long x;
//	    long y;
//	    No vi = null;
//	    //No yo;
//	    System.out.print("\n Programa Arvore binaria de long");
//	    do {
//	        System.out.print("\n***********************************");
//	        System.out.print("\nEntre com a opcao:");
//		System.out.print("\n ----1: Inserir");
//		System.out.print("\n ----2: Excluir");
//		System.out.print("\n ----3: Alterar");
//		//System.out.print("\n ----5: Pesquisar");
//		System.out.print("\n ----4: Exibir");
//		System.out.print("\n ----5: Pesquisar");
//		System.out.print("\n ----6: Sair do programa");
//		//System.out.print("\n ----5: Alterar");
//		System.out.print("\n***********************************");
//		System.out.print("\n-> ");
//		opcao = le.nextInt();
//		switch(opcao) {
//		
//		//inserir/criar
//		 	case 1: {
//			       System.out.print("\n Informe o valor (long) -> ");
//			       x = le.nextLong();
//			       arv.inserir(x);
//			       break;
//			}
//		 	
//		 	//remover
//			case 2: {
//			       System.out.print("\n Informe o valor (long) -> ");
//			       x = le.nextLong();
//			       if ( !arv.remover(x) )
//	                          System.out.print("\n Valor nao encontrado!");;
//			       break;
//			   
//			}
//			
//			//alterar
//			case 3: {
//				System.out.println("Informe o valor atual (long) ->");
//					x = le.nextLong();
//				System.out.println("Digite o novo valor (long) ->");
//				    y = le.nextLong();
//				   
//				if( arv.buscar(x) != null  )
//					vi = arv.buscar(x) ;
//				    vi.item=y;
//				
//					
//					
//				
//			}
//			case 5: {
//			       System.out.print("\n Informe o valor (long) -> ");
//	                       x = le.nextLong();
//		      	       if( arv.buscar(x) != null )
//			          System.out.print("\n Valor Encontrado");
//			       else 
//			          System.out.print("\n Valor nao encontrado!");
//			       break;
//			}	 
//			case 4: {
//			      arv.caminhar();
//			      break; 
//			}
//	        } // fim switch
//	    } while(opcao != 6);
//	    le.close();
//
//	  }
//	}