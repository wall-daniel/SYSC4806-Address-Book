$(document).ready(function() {

    $('#new-buddy-form').submit((e) => {
        e.preventDefault();
        let buddyinfo = getFormData($('#new-form'));

        $.ajax({
            type: 'POST',
            url: '/addressbooks/' + addressId + '/buddies',
            dataType: 'json',
            data: JSON.stringify(buddyinfo),
            contentType : 'application/json',
            success: function(data) {
                window.location.href = "/addresses/" + addressId;
            }
        });
    })
});

function getFormData($form){
    let unindexed_array = $form.serializeArray();
    let indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}