$('.change-user').change((event) => {
    let count = event.target.getAttribute('count');
    $(`button[count="${count}"]`).removeAttr('disabled');
})