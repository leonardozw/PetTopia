(function () {
    $("#tabclientes").on("click", ".js-delete", function () {
        let botaoClicado = $(this);
        $("#btnsim").attr("data-id", botaoClicado.attr("data-id"));
        $("#modalcliente").modal("show");
    });
    $("#btnsim").on("click", function () {
        let botaoSim = $(this);
        let id = botaoSim.attr("data-id");
        $.ajax({
            url: "/cliente/remover/" + id,
            method: "GET",
            success: function () {
                window.location.href = "/cliente";
            }
        });
    });

    $("#tabprodutos").on("click", ".js-delete", function () {
        let botaoClicado = $(this);
        $("#btnsim").attr("data-id", botaoClicado.attr("data-id"));
        $("#modalproduto").modal("show");
    });
    $("#btnsim").on("click", function () {
        let botaoSim = $(this);
        let id = botaoSim.attr("data-id");
        $.ajax({
            url: "/produto/remover/" + id,
            method: "GET",
            success: function () {
                window.location.href = "/produto";
            }
        });
    });

    $("#tabcategorias").on("click", ".js-delete", function () {
        let botaoClicado = $(this);
        $("#btnsim").attr("data-id", botaoClicado.attr("data-id"));
        $("#modalcategoria").modal("show");
    });
    $("#btnsim").on("click", function () {
        let botaoSim = $(this);
        let id = botaoSim.attr("data-id");
        $.ajax({
            url: "/categoria/remover/" + id,
            method: "GET",
            success: function () {
                window.location.href = "/categoria";
            }
        });
    });

    $("#tabpets").on("click", ".js-delete", function () {
        let botaoClicado = $(this);
        $("#btnsim").attr("data-id", botaoClicado.attr("data-id"));
        $("#modalpet").modal("show");
    });
    $("#btnsim").on("click", function () {
        let botaoSim = $(this);
        let id = botaoSim.attr("data-id");
        $.ajax({
            url: "/pet/remover/" + id,
            method: "GET",
            success: function () {
                window.location.href = "/pet";
            }
        });
    });

    function filtrarProdutos() {
        var pesquisa = document.getElementById("pesquisaProduto").value.toLowerCase();

        var linhasProdutos = document.querySelectorAll("#tabprodutos tbody tr");

        for (var i = 0; i < linhasProdutos.length; i++) {
            var nomeProduto = linhasProdutos[i].querySelector("td:first-child").textContent.toLowerCase();

            if (nomeProduto.includes(pesquisa)) {
                linhasProdutos[i].style.display = "";
            } else {
                linhasProdutos[i].style.display = "none";
            }
        }
    }
    function filtrarCategoria() {
        var pesquisa = document.getElementById("pesquisaCategoria").value.toLowerCase();

        var linhasCategorias = document.querySelectorAll("#tabcategorias tbody tr");

        for (var i = 0; i < linhasCategorias.length; i++) {
            var nomeCategoria = linhasCategorias[i].querySelector("td:first-child").textContent.toLowerCase();

            if (nomeCategoria.includes(pesquisa)) {
                linhasCategorias[i].style.display = "";
            } else {
                linhasCategorias[i].style.display = "none";
            }
        }
    }
    function filtrarCliente() {
        var pesquisa = document.getElementById("pesquisaCliente").value.toLowerCase();

        var linhasClientes = document.querySelectorAll("#tabclientes tbody tr");

        for (var i = 0; i < linhasClientes.length; i++) {
            var nomeCliente = linhasClientes[i].querySelector("td:first-child").textContent.toLowerCase();

            if (nomeCliente.includes(pesquisa)) {
                linhasClientes[i].style.display = "";
            } else {
                linhasClientes[i].style.display = "none";
            }
        }
    }

    function filtrarPet() {
        var pesquisa = document.getElementById("pesquisaPet").value.toLowerCase();

        var linhasPets = document.querySelectorAll("#tabpets tbody tr");

        for (var i = 0; i < linhasPets.length; i++) {
            var nomePet = linhasPets[i].querySelector("td:first-child").textContent.toLowerCase();

            if (nomePet.includes(pesquisa)) {
                linhasPets[i].style.display = "";
            } else {
                linhasPets[i].style.display = "none";
            }
        }
    }

    function filterByHigherPrice() {
        var filtro = "maiorPreco"; 
        $.ajax({
            url: '/home/filtrar',
            type: 'GET',
            data: { filtro: filtro },
            success: function (response) {
                $('#productsContainer').html(response); 
            },
            error: function (xhr, status, error) {
                
            }
        });
    }

    function filterByLowerPrice() {
        var filtro = "menorPreco";
        $.ajax({
            url: '/home/filtrar',
            type: 'GET',
            data: { filtro: filtro },
            success: function (response) {
                $('#productsContainer').html(response); 
            },
            error: function (xhr, status, error) {
                
            }
        });
    }

    function filterByCategoria(element) { 
        var categoriaId = $(element).data('categoria')
        $.ajax({
            url: '/home/filtrar/categoria',
            type: 'GET',
            data: { categoria: categoriaId },
            success: function (response) {
                console.log(response)
                $('#productsContainer').html(response); 
            },
            error: function (xhr, status, error) {
                
            }
        });
    }

    function performSearch() {
        var searchValue = $('#searchInput').val();
        $.ajax({
            url: '/home/pesquisar',
            type: 'GET',
            data: { search: searchValue },
            success: function(response) {
                $('#productsContainer').html(response);
            },
            error: function(xhr, status, error) {
            
            }
        });
    }

    window.filtrarPet = filtrarPet;
    window.filtrarCliente = filtrarCliente;
    window.filtrarCategoria = filtrarCategoria;
    window.filtrarProdutos = filtrarProdutos;
    window.filterByHigherPrice = filterByHigherPrice;
    window.filterByLowerPrice = filterByLowerPrice;
    window.filterByCategoria = filterByCategoria;
    window.performSearch = performSearch;
})();