(function(){
    $("#tabclientes").on("click",".js-delete", function(){
        let botaoClicado = $(this);
        $("#btnsim").attr("data-id", botaoClicado.attr("data-id"));
        $("#modalcliente").modal("show");
    });
    $("#btnsim").on("click", function(){
        let botaoSim = $(this);
        let id = botaoSim.attr("data-id");
        $.ajax({
            url: "/cliente/remover/" + id,
            method: "GET",
            success: function(){
                window.location.href = "/cliente";
            }
        });
    });

    $("#tabprodutos").on("click",".js-delete", function(){
        let botaoClicado = $(this);
        $("#btnsim").attr("data-id", botaoClicado.attr("data-id"));
        $("#modalproduto").modal("show");
    });
    $("#btnsim").on("click", function(){
        let botaoSim = $(this);
        let id = botaoSim.attr("data-id");
        $.ajax({
            url: "/produto/remover/" + id,
            method: "GET",
            success: function(){
                window.location.href = "/produto";
            }
        });
    });

    $("#tabcategorias").on("click",".js-delete", function(){
        let botaoClicado = $(this);
        $("#btnsim").attr("data-id", botaoClicado.attr("data-id"));
        $("#modalcategoria").modal("show");
    });
    $("#btnsim").on("click", function(){
        let botaoSim = $(this);
        let id = botaoSim.attr("data-id");
        $.ajax({
            url: "/categoria/remover/" + id,
            method: "GET",
            success: function(){
                window.location.href = "/categoria";
            }
        });
    });

})();