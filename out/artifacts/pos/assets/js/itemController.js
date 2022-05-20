/**
 *@Owner - Oshada Eranga
 *@Version - v0.1.0
 **/

$(document).ready(function () {
        $("#btnSaveItem").click(function () {
            // alert("Add");

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
                    let row = `<tr><td>${item.code}</td><td>${item.name}</td><td>${item.price}</td><td>${item.qtyOnHand}</td></tr>`;
                    $("#itemTable").append(row);
                }
                bindClickEvents();
            });
        }

        $("#btnDeleteItem").click(function () {
            let itemCode = $("#txtItemCode").val();

            var settings = {
                "url": "http://localhost:8080/pos/item?itemCode" + itemCode,
                "method": "DELETE",
            };
            $.ajax(settings).done(function (response) {
                alert(response);
            });
        });

        $("#btnUpdateItem").click(function () {
            let formData = $("#itemForm").serialize();
            /*console.log(formData);*/
            var settings = {
                "url": "http://localhost:8080/pos/item?itemCode?" + formData,
                "method": "PUT"
            }
            $.ajax(settings).done(function (response) {
                alert(response);
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

//
//         });
//
//
//     });
//
//     loadAllCustomers();
//
//     function loadAllCustomers() {
//         $("#itemTable").empty();
//         $.ajax({
//             url: "item",
//             method: "GET",
//             // dataType:"json", // please convert the response into JSON
//             success: function (resp) {
//                 console.log(typeof resp);
//                 for (const item of resp) {
//                     let row = `<tr><td>${item.code}</td><td>${item.name}</td><td>${item.unitPrice}</td><td>${item.qtyOnHand}</td></tr>`;
//                     $("#itemTable").append(row);
//                 }
//                 bindClickEvents();
//             }
//         });
//
//     }
//
//     function bindClickEvents() {
//         $("#itemTable>tr").click(function () {
//             //Get values from the selected row
//             let id = $(this).children().eq(0).text();
//             let name = $(this).children().eq(1).text();
//             let unitPrice = $(this).children().eq(2).text();
//             let qtyOnHand = $(this).children().eq(3).text();
//
//             //Set values to the text-fields
//             $("#txtItemCode").val(id);
//             $("#txtItemName").val(name);
//             $("#txtUnitPrice").val(unitPrice);
//             $("#txtQtyOnHand").val(qtyOnHand);
//         });
//     }
// });