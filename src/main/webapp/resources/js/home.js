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

$('#all-items').click(function (event) {
    event.preventDefault();
    $.get('/items/all', {}, function (data) {
        let template = $('#template-all-items');
        let parent = $('#parent');
        parent.addClass('py-4');
        data.forEach((item) => {
            let clone = template[0].content.cloneNode(true);
            clone.querySelector('#title').innerHTML = item.name;
            clone.querySelector('#title').setAttribute('href', `/items/${item.name}?id=${item.id}`);
            item.tags.forEach((tag) => {
                let li = document.createElement('li');
                let a = document.createElement('a');
                a.innerHTML = tag.name;
                a.setAttribute('href', `/tags/${tag.name}?id=${tag.id}`);
                li.append(a);
                clone.querySelector('#tags').append(li);
            })
            clone.querySelector('#description').innerHTML = item.description;
            parent.append(clone);
        })
    })
})

$('#all-collections').click(function (event) {
    event.preventDefault();
    $.get('/collections/all', {}, function (data) {
        let template = $('#template-all-items');
        let parent = $('#parent');
        parent.addClass('py-4');
        data.forEach((item) => {
            let clone = template[0].content.cloneNode(true);
            clone.querySelector('#title').innerHTML = item.name;
            clone.querySelector('#title').setAttribute('href', `/collection/${item.name}?id=${item.id}`);
            item.tags.forEach((tag) => {
                let li = document.createElement('li');
                let a = document.createElement('a');
                a.innerHTML = tag.name;
                a.setAttribute('href', `/tags/${tag.name}?id=${tag.id}`);
                li.append(a);
                clone.querySelector('#tags').append(li);
            })
            clone.querySelector('#description').innerHTML = item.description;
            parent.append(clone);
        })
    })
})
