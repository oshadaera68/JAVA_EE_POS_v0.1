/**
 *@Owner - Oshada Eranga
 *@Version - v0.1.0
 **/

$(document).ready(function () {
        $("#btnSaveItem").click(function () {
            var settings = {
                "url": "http://localhost:8080/pos/item",
                "method": "POST",
                "headers": {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                "data": {
                    "txtItemCode": $("#txtItemCode").val(),
                    "txtItemName": $("#txtItemName").val(),
                    "txtUnitPrice": $("#txtUnitPrice").val(),
                    "txtQtyOnHand": $("#txtQtyOnHand").val()
                },
            };

            $.ajax(settings).done(function (response) {
                alert(response);
            });
        });

        loadAllItems();



        $("#btnDeleteItem").click(function () {
            let itemCode = $("#txtItemCode").val();

            var settings = {
                "url": "http://localhost:8080/pos/item?itemCode" + itemCode,
                "method": "DELETE",
            };

            $.ajax(settings).done(function (response) {
                alert(response);
                console.log(response);
                if (response.status === 200) {
                    alert(response.message);
                    loadAllItems();
                } else if (response.status === 400) {
                    alert(response.data);
                } else {
                    alert(response.data);
                }
            });
        });

        $("#btnUpdateItem").click(function () {
            let formData = $("#itemForm").serialize();
            var settings = {
                "url": "http://localhost:8080/pos/item?itemCode?" + formData,
                "method": "PUT",
            }

            $.ajax(settings).done(function (response) {
                if (response.status === 200) {
                    alert(response.message);
                    loadAllItems();
                } else if (response.status === 400) {
                    alert(response.message);
                } else {
                    alert(response.data);
                }
            });
        });
    }
);


function bindClickEvents() {
    $("#itemTable>tr").click(function () {
        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let unitPrice = $(this).children().eq(2).text();
        let qtyOnHand = $(this).children().eq(3).text();

        $("#txtItemCode").val(id);
        $("#txtItemName").val(name);
        $("#txtUnitPrice").val(unitPrice);
        $("#txtQtyOnHand").val(qtyOnHand);
    });
}

$("#btnLoadAllItem").click(function () {
    loadAllItems();
});

function loadAllItems() {
    var settings = {
        "url": "http://localhost:8080/pos/item",
        "method": "GET",
    };

    $.ajax(settings).done(function (response) {
        console.log(typeof response);
        for (const item of response) {
            let row = `<tr><td>${item.id}</td><td>${item.name}</td><td>${item.unitPrice}</td><td>${item.qtyOnHand}</td></tr>`;
            $("#itemTable").append(row);
        }
        bindClickEvents();
    });
}