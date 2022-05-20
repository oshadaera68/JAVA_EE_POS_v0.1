/**
 *@Owner - Oshada Eranga
 *@Version - v0.1.0
 **/
$(document).ready(function () {
        $("#btnSaveCustomer").click(function () {
            var settings = {
                "url": "http://localhost:8080/pos/customer",
                "method": "POST",
                "headers": {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                "data": {
                    "txtCusID": $("#txtCusID").val(),
                    "txtName": $("#txtName").val(),
                    "txtAddress": $("#txtAddress").val(),
                    "txtSalary": $("#txtSalary").val()
                },
            };

            $.ajax(settings).done(function (response) {
                alert(response);
            });
        });

        loadAllCustomer();

        $("#btnLoadAllCustomer").click(function () {
            loadAllCustomer();
        });

        function loadAllCustomer() {
            var settings = {
                "url": "http://localhost:8080/pos/customer",
                "method": "GET",
            };

            $.ajax(settings).done(function (response) {
                console.log(typeof response);
                for (const customer of response) {
                    let row = `<tr><td>${customer.id}</td><td>${customer.name}</td><td>${customer.address}</td><td>${customer.salary}</td></tr>`;
                    $("#customerTable").append(row);
                }
                bindClickEvents();
            });
        }

        $("#btnDeleteCustomer").click(function () {
            let cusId = $("#txtCusID").val();

            var settings = {
                "url": "http://localhost:8080/pos/customer?CusID" + cusId,
                "method": "DELETE",
            };
            $.ajax(settings).done(function (response) {
                alert(response);
                console.log(response);
                if (response.status === 200) {
                    alert(response.message);
                    loadAllCustomer();
                } else if (response.status === 400) {
                    alert(response.data);
                } else {
                    alert(response.data);
                }
            });
        });

        $("#btnUpdateCustomer").click(function () {
            let formData = $("#customerTable").serialize();
            /*console.log(formData);*/
            var settings = {
                "url": "http://localhost:8080/pos/item?customerId?" + formData,
                "method": "PUT",
                "data": "formData"
            }
            $.ajax(settings).done(function (response) {
                alert(response);
                console.log(response);
                if (response.status === 200) {
                    alert(response.message);
                    loadAllCustomer();
                } else if (response.status === 400) {
                    alert(response.data);
                } else {
                    alert(response.data);
                }
            });
        });
    }
);

function bindClickEvents() {
    $("#customerTable>tr").click(function () {
        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let address = $(this).children().eq(2).text();
        let salary = $(this).children().eq(3).text();

        $("#txtCusID").val(id);
        $("#txtName").val(name);
        $("#txtAddress").val(address);
        $("#txtSalary").val(salary);
    });
}