const getServicesURL = 'http://localhost:8080/getServices';
const addServiceURL = 'http://localhost:8080/addService';
const ul = document.getElementById('services');

fetch(getServicesURL)
    .then((resp) => resp.json())
    .then(function (data) {
        return data.map(function (service) {
            let li = createNode('li');
            let span = createNode('span');
            let added = new Date(service.added).toLocaleString();
            let latestUpdate = new Date(service.latestUpdate).toLocaleString()
            span.innerHTML = `${service.name} ${service.url} ${service.upResponse} ${added} ${latestUpdate}`;
            append(li, span);
            append(ul, li);
        })
    })
    .catch(function (error) {
        console.log(error);
    });

function createNode(element) {
    return document.createElement(element);
}

function append(parent, el) {
    return parent.appendChild(el);
}

function addService() {
    const name = document.getElementById('name').value;
    const url = document.getElementById('url').value;
    const data = { name: name, url: url}
    fetch(addServiceURL, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(() => {
        console.log("Added service")
    })
}