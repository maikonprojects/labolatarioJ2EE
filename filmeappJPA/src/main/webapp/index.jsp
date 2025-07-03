<!-- src/main/webapp/index.jsp -->

<%@ page import="java.util.List" %>
<%@ page import="br.com.filmeapp.model.Filme" %>

<html>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<body>

<div class="container-pai">

	<div>
			<h1>Minha lista de filmes:</h1>
		<div>
			<table>
			    <thead>
			        <tr>
			            <th>Nome</th>
			            <th>Nota</th>
			            <th>Deletar</th>
			            <th>Editar</th>
			        </tr>
			    </thead>
			    <div class="box-addFilme">
			     <button class="post-btn">
					     <i class="fa-solid fa-plus"></i>
		       </button>
		       <h3>Adicionar</h3>
			    </div>
			   
			    <tbody>
			        <%
			            List<Filme> filmes = (List<Filme>) request.getAttribute("filmes");
			            if (filmes != null) {
			                for (Filme f : filmes) {
			        %>
			        <tr>
			            <td><%= f.getNome() %></td>
			            <td><%= f.getNota() %></td>
			            <td>
			            <form action="filmes" method="post">
						    <input type="hidden" name="action" value="delete" />
						    <input type="hidden" value="<%= f.getId() %>" name="idFilme" placeholder="Digite o código do filme" required>
						    <button class="close" type="submit"><i class="fas fa-trash-alt"></i></button>
						</form>
			            </td>
			           
			           <td>
					   <button class="edit-btn" 
						        data-id="<%= f.getId() %>" 
						        data-nome="<%= f.getNome() %>" 
						        data-nota="<%= f.getNota() %>">
						    <i class="fa-solid fa-pen"></i>
						</button>
						</td>
						
			        </tr>
			        <%
			                }
			            }
			        %>
			    </tbody>
			</table>
		</div>
	

	</div>
	
</div>
    <!-- Modal de edição -->
<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close-modal">&times;</span>
        <h2>Editar Filme</h2>
        <form action="filmes" method="post">
            <input type="hidden" name="action" value="put" />
            <input type="hidden" name="idFilme" id="modalIdFilme" />
            <input type="text" name="nomeFilme" id="modalNomeFilme" placeholder="Novo nome do filme" required />
            <input type="text" name="notaFilme" id="modalNotaFilme" placeholder="Nova nota do filme" required />
            <button class="put" type="submit">Salvar Alterações</button>
        </form>
    </div>
</div>
    
<div id="postModal" class="modal">
    <div class="modal-content">
        <span class="close-modal-post">&times;</span>
        <h2>Inserir Filme</h2>
        <form action="filmes" method="post">
		  		<input type="hidden" name="action" value="post" />
		        <input type="text" name="nomeFilme" placeholder="Digite o nome do filme" required />
		        <input type="text" name="notaFilme" placeholder="Digite a nota do filme" required />
		        <button class="post" type="submit">Adicionar Filme</button>
		 </form>
    </div>
</div>

<div id="postNotaModal" class="modal">
    <div class="modal-content">
        <span class="close-modal-post-nota">&times;</span>
        <h2>Avaliação filme</h2>
        <form action="filmes" method="post">
		  		<input type="hidden" name="action" value="post" />
		        <input type="text" name="nota" placeholder="Digite a nota do filme" required />
		        <button class="post" type="submit">Dar nota</button>
		 </form>
    </div>
</div>
    
</body>
</html>



<script>
    // Abrir o modal e preencher os dados
    document.querySelectorAll(".edit-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const modal = document.getElementById("editModal");
            document.getElementById("modalIdFilme").value = btn.dataset.id;
            document.getElementById("modalNomeFilme").value = btn.dataset.nome;
            document.getElementById("modalNotaFilme").value = btn.dataset.nota;
            modal.style.display = "block";
        });
    });
    
 // Abrir o modal post
    document.querySelectorAll(".post-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const modal = document.getElementById("postModal");
            
            modal.style.display = "block";
        });
    });
 
    document.querySelectorAll(".post-nota-btn").forEach(btn => {
        btn.addEventListener("click", () => {
            const modal = document.getElementById("postNotaModal");
            
            modal.style.display = "block";
        });
    });

    // Fechar o modal put
    document.querySelector(".close-modal").onclick = function () {
        document.getElementById("editModal").style.display = "none";
    }
    
 	// Fechar o modal post
    document.querySelector(".close-modal-post").onclick = function () {
        document.getElementById("postModal").style.display = "none";
    }

 // Fechar o modal post
    document.querySelector(".close-modal-post-nota").onclick = function () {
        document.getElementById("postNotaModal").style.display = "none";
    }

</script>

