<!-- src/main/webapp/index.jsp -->
<html>
<head>
    <title>Calculadora</title>
    <script>
        function enviar(operacao) {
            document.getElementById("operacao").value = operacao;
            document.getElementById("formCalc").submit();
        }
    </script>
</head>
<body>
    <div>
        <h1>Calculadora</h1>
        <form id="formCalc" method="post" action="calc">
            <input type="number" name="num1" placeholder="Número 1" required>
            <input type="number" name="num2" placeholder="Número 2" required>
            <input type="hidden" name="operacao" id="operacao">
            <br><br>
            <button type="button" onclick="enviar('+')">+</button>
            <button type="button" onclick="enviar('-')">-</button>
            <button type="button" onclick="enviar('*')">*</button>
            <button type="button" onclick="enviar('/')">/</button>
        </form>
    </div>
    <% if (request.getAttribute("mensagem") != null) { %>
    <h3><%= request.getAttribute("mensagem") %></h3>
<% } %>

</body>
</html>
