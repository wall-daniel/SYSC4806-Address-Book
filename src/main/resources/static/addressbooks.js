let addressId = -1;

$(document).ready(function() {

    $('#new-address-book-form').submit((e) => {
        e.preventDefault();
        let addressbook = getFormData($('#new-address-book-form'));
        let json = JSON.stringify(addressbook)

        $.ajax({
            type: 'POST',
            url: '/addressbooks',
            dataType: 'json',
            data: JSON.stringify(addressbook),
            contentType : 'application/json',
            success: function(data) {
                alert('Created new address book.');
                loadAddressBooks();
            }
        });
    });

    $('#new-buddy-form').submit((e) => {
        e.preventDefault();

        if (addressId == -1) {
            alert("First select an address book");
            return;
        }

        let buddyinfo = getFormData($('#new-buddy-form'));
        let json = JSON.stringify(buddyinfo)

        $.ajax({
            type: 'POST',
            url: '/addressbooks/' + addressId + '/buddies',
            dataType: 'json',
            data: JSON.stringify(buddyinfo),
            contentType : 'application/json',
            success: function(data) {
                alert('Created new buddy.');
                loadAddressBook(addressId);
            }
        });
    });

    // Load the address books and put them in the list
    loadAddressBooks();
});

function getFormData($form){
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function loadAddressBooks() {
    $.ajax({
        type: 'GET',
        url: '/addressbooks',
        contentType : 'application/json',
        success: function(data) {
            // Find the table
            let table = document.getElementById('address-books-table');
            table.innerHTML = '';

            // Create rows from data
            data.forEach((addressBook) => {
                let row = table.appendChild(document.createElement('tr'));

                let name = row.appendChild(document.createElement('td'));
                name.innerText = addressBook['name'];
                let id = row.appendChild(document.createElement('td'));
                id.innerText = addressBook['id'];
                let size = row.appendChild(document.createElement('td'));
                size.innerText = addressBook['size'];
                let link = row.appendChild(document.createElement('td'));
                let button = link.appendChild(document.createElement('a'));
                button.innerText = 'address book ' + addressBook['id'];
                button.onclick = () => {
                    loadAddressBook(addressBook['id']);
                    return false;
                };
                button.href = '';
            });
        }
    })
}

function loadAddressBook(addressBookId) {
    // Set the currently selected address book id
    addressId = addressBookId;

    $.ajax({
        type: 'GET',
        url: '/addressbooks/' + addressId,
        contentType: 'application/json',
        success: function (data) {
            // Get the table and remove all elements inside
            let table = document.getElementById('buddy-info-table');
            table.innerHTML = '';

            data['buddies'].forEach((buddy) => {
                let row = table.appendChild(document.createElement('tr'));

                let name = row.appendChild(document.createElement('td'));
                name.innerText = buddy['name'];
                let number = row.appendChild(document.createElement('td'));
                number.innerText = buddy['number'];
                let id = row.appendChild(document.createElement('td'));
                id.innerText = buddy['id'];
            })
        }
    });

}