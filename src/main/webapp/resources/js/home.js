function findTitle(fields, name) {
    let title;
    fields.forEach((item) => {
        if (item.classField == name)
            title = item.title;
    })
    return title;
}

function removeItems() {
    let references = new Map();
    $('.item-title a').each((index, item) => {
        references.set(item.getAttribute('href'), item.textContent);
        $('#card').detach();
    })
    return references;
}

function createMenuItems(references) {
    references.forEach((value, key) => {
        let menuLi = createDOMElement('li', {
            class: 'menu-item-dark'
        });
        let menuItem = createDOMElement('a', {
            href: key
        }, value);
        menuLi.append(menuItem);
        $('#menu-items-container').attr('class', 'col-4');
        $('#menu-items').append(menuLi);
    })
}

function createDOMElement(type, attributes, data) {
    let newElement = document.createElement(type);
    $(newElement).attr(attributes);
    if (data !== undefined) newElement.innerHTML = data;
    return newElement;
}

function fieldsToString(array) {
    let str;
    if (!Array.isArray(array)) return array.name;
    array.forEach((item) => {
        str += item.name + ', '
    })
    return str;
}

$('.all-items').click(function (event) {
    event.preventDefault();
    $.get('/items/all', {}, function (data) {
        $('#set-of-cards').empty();
        let template = $('#template-all-items');
        let header = $('#template-header');
        let parent = $('#set-of-cards');
        let headerClone = header[0].content.cloneNode(true);
        headerClone.querySelector('h1').innerHTML = "Collection's items";
        parent.append(headerClone);
        data.forEach((item) => {
            let clone = template[0].content.cloneNode(true);
            clone.querySelector('#item-title').innerHTML = item.name;
            clone.querySelector('#item-title').setAttribute('href', `/items/${item.name}?id=${item.id}`);
            item.tags.forEach((tag) => {
                let li = document.createElement('li');
                let a = document.createElement('a');
                a.innerHTML = tag.name;
                a.setAttribute('href', `/tags/${tag.name}?id=${tag.id}`);
                li.append(a);
                clone.querySelector('#item-tags').append(li);
            })
            clone.querySelector('#item-description').innerHTML = item.description;
            parent.append(clone);
        })
    })
})

$('.all-collections').click(function (event) {
    event.preventDefault();
    $.get('/collections/all', {}, function (data) {
        $('#set-of-cards').empty();
        let template = $('#template-all-items');
        let header = $('#template-header');
        let parent = $('#set-of-cards');
        let headerClone = header[0].content.cloneNode(true);
        headerClone.querySelector('h1').innerHTML = "User's collections";
        parent.append(headerClone);
        data.forEach((item) => {
            let clone = template[0].content.cloneNode(true);
            clone.querySelector('#item-title').innerHTML = item.name;
            clone.querySelector('#item-title').setAttribute('href', `/collection/${item.name}?id=${item.id}`);
            item.tags.forEach((tag) => {
                let li = document.createElement('li');
                let a = document.createElement('a');
                a.innerHTML = tag.name;
                a.setAttribute('href', `/tags/${tag.name}?id=${tag.id}`);
                li.append(a);
                clone.querySelector('#item-tags').append(li);
            })
            clone.querySelector('#item-description').innerHTML = item.description;
            parent.append(clone);
        })
    })
})

$('.tag-ref').click(function (event) {
    event.preventDefault();
    $.ajax({
        url: event.target.getAttribute('href'),
        method: 'GET',
        dataType: 'JSON',
        success: function (data) {
            $('#set-of-tag-cards').empty();
            let template = $('#template-all-items');
            let header = $('#template-header');
            let parent = $('#set-of-tag-cards');
            let headerClone = header[0].content.cloneNode(true);
            headerClone.querySelector('h1').innerHTML = `User's items and collections with tag '${event.target.textContent}'`;
            parent.append(headerClone);
            data.forEach((item) => {
                let clone = template[0].content.cloneNode(true);
                clone.querySelector('#item-title').innerHTML = item.name;
                clone.querySelector('#item-title').setAttribute('href', `/collection/${item.name}?id=${item.id}`);
                item.tags.forEach((tag) => {
                    let li = document.createElement('li');
                    let a = document.createElement('a');
                    a.innerHTML = tag.name;
                    a.setAttribute('href', `/tags/${tag.name}?id=${tag.id}`);
                    li.append(a);
                    clone.querySelector('#item-tags').append(li);
                })
                clone.querySelector('#item-description').innerHTML = item.description;
                parent.append(clone);
            })
        },
        error: function () {
            $('#set-of-tag-cards').empty();
            $('#set-of-tag-cards').html(`No items and collection with tag '${event.target.textContent}'`);
        }
    })
})

$('#search-btn').click(function (event) {
    event.preventDefault();
    console.log($('#search-input')[0].value);
    $.ajax({
        url: '/items/search?='+$('#search-input')[0].value,
        method: 'GET',
        dataType: 'JSON',
        success: function (data) {
            console.log(data);
        },
        error: function () {
            console.log('error');
        }
    })
})

$('#edit-user-description').click(function (event) {
    event.preventDefault();
    $('#save-user-description').removeAttr('hidden');
    $('#user-description').removeAttr('readOnly');
})


$('#save-user-description').click(function (event) {
    $.post('/user/edit', {
        text: description.text.value
    });
    $('#save-user-description').attr('hidden', true);
})

$('#password, #repeatPassword').on('keyup', function () {
    if ($('#password').val() == $('#repeatPassword').val()) {
        $('#message').html('Matching').css('color', 'green');
    } else
        $('#message').html('Not Matching').css('color', 'red');
});
