$(document).ready(() => {
    const virusGroup = $('#viruses');
    const tableElement = $("#table");
    virusGroup.on('change', () => {
        $('#choice').text('All Viruses');
        tableElement.empty();
        const tableHeader = '<th scope="col">#</th>\n' +
            '      <th scope="col">Name</th>\n' +
            '      <th scope="col">Magnitude</th>\n' +
            '      <th scope="col">Released On</th>' +
            '      <th scope="col">Actions</th>';
        tableElement.append('<tr>');
        tableElement.append(tableHeader);
        $.getJSON("/api/viruses/all", (viruses) => {
            viruses.forEach((virus, index) => {
                const virusEditUrl = "/viruses/edit/?id=" + virus.id;
                const virusDeleteUrl = "/viruses/delete/?id=" + virus.id;
                const tableRow = '<tr>\n' +
                    '      <td>' + index + '</td>\n' +
                    '      <td>' + virus.name + '</td>\n' +
                    '      <td>' + virus.magnitude + '</td>\n' +
                    '      <td>' + virus.releasedOn.split(/T/)[0] + '</td>\n' +
                    `      <td><a class="btn btn-success" href="${virusEditUrl}">Edit</a></td>` +
                    `      <td><a class="btn btn-danger" href="${virusDeleteUrl}">Delete</a></td>` +
                    '    </tr>';
                tableElement.append(tableRow);
            })
        });
        tableElement.append('</tr>');
    });

    const capitalGroup = $('#capitals');
    capitalGroup.on('change', () => {
        $('#choice').text('All Capitals');
        tableElement.empty();
        const tableHeader = '<th scope="col">#</th>\n' +
            '      <th scope="col">Name</th>\n' +
            '      <th scope="col">Latitude</th>\n' +
            '      <th scope="col">Longitude</th>' +
            tableElement.append('<tr>');
        tableElement.append(tableHeader);
        $.getJSON("/api/capitals/all", (capitals) => {
            capitals.forEach((capital, index) => {
                const tableRow = '<tr>\n' +
                    '      <td>' + index + '</td>\n' +
                    '      <td>' + capital.name + '</td>\n' +
                    '      <td>' + capital.latitude + '</td>\n' +
                    '      <td>' + capital.longitude + '</td>\n' +
                    '    </tr>';
                tableElement.append(tableRow);
            })
        });
        tableElement.append('</tr>');
    })
});