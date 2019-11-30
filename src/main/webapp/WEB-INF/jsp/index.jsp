<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>monitoring</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
    <style>

        .wrapper {
            text-align: center;
            padding: 2vh;
        }

        .grow:hover {
            -webkit-transform: scale(1.1);
            -ms-transform: scale(1.1);
            transform: scale(1.1);
            transition: 1s;
        }

        .grow {
            transition: 1s;
        }

        .input-group {
            padding: 3vh;
        }

        button {
            margin: 1vh;
        }

        .double-flash {
            animation: double-flash_1084 2s ease infinite;
            transform-origin: 50% 50%;
        }

        @keyframes double-flash_1084 {
            0% {
                opacity: 1
            }
            25% {
                opacity: 0
            }
            50% {
                opacity: 1
            }
            75% {
                opacity: 0
            }
            100% {
                opacity: 1
            }
        }

        .passing {
            animation: passing_4560 2s linear infinite;
            transform-origin: 50% 50%;
            position: relative;
            top: 15px;
        }

        @keyframes passing_4560 {
            0% {
                transform: translateX(-50%);
                opacity: 0
            }
            50% {
                transform: translateX(0%);
                opacity: 1
            }
            100% {
                transform: translateX(50%);
                opacity: 0
            }
        }

        .float {
            animation: float_4489 2s linear infinite;
            transform-origin: 50% 50%;
        }

        @keyframes float_4489 {
            0% {
                transform: translateY(0)
            }
            50% {
                transform: translateY(-5px)
            }
            100% {
                transform: translateY(0)
            }
        }


        body {
            background-color: #FFFAFA;
        }

        .critical {
            animation: critical_9754 2s linear infinite;
            transform-origin: 50% 50%;
        }

        @keyframes critical_9754 {
            0% {
                transform: scale(1)
            }
            12.5% {
                transform: scale(.9) rotate(-8deg)
            }
            25% {
                transform: scale(.9) rotate(-8deg)
            }
            37.5% {
                transform: scale(1.3) rotate(8deg)
            }
            50% {
                transform: scale(1.3) rotate(-8deg)
            }
            62.5% {
                transform: scale(1.3) rotate(8deg)
            }
            75% {
                transform: scale(1.3) rotate(-8deg)
            }
            87.5% {
                transform: scale(1.3) rotate(8deg)
            }
            100% {
                transform: scale(1) rotate(0)
            }
        }

        .double-flash {
            animation: double-flash_8519 2s ease infinite;
            transform-origin: 50% 50%;
        }

        @keyframes double-flash_8519 {
            0% {
                opacity: 1
            }
            25% {
                opacity: 0
            }
            50% {
                opacity: 1
            }
            75% {
                opacity: 0
            }
            100% {
                opacity: 1
            }
        }

        .pulse {
            animation: pulse_1748 2s linear infinite;
            transform-origin: 50% 50%;
            position: relative;
            top: 15px;
        }

        @keyframes pulse_1748 {
            0% {
                transform: scale(1.1)
            }
            50% {
                transform: scale(0.8)
            }
            100% {
                transform: scale(1.1)
            }
        }
    </style>
</head>
<body>
<br/>
<h1 align="center">Site List</h1>
<br/>

<div class="container shadow-lg p-3 mb-5 bg-white rounded" style="min-width: 80%">
    <br/>
    <table align=" center" class="table" style="width: 90%; margin: auto">
        <thead class="thead-dark">
        <tr align="center">
            <th>id</th>
            <th>Url</th>
            <th>Monitoring</th>
            <th>Time</th>
            <th>MinMax</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <c:forEach items="${listSite}" var="site" varStatus="loop">
            <tr class="grow" align="center">
                <td style="vertical-align: middle;"><span>${site.id}</span></td>
                <td style="vertical-align: middle;">
                    <div style="overflow: hidden; max-width: 50vh">
                        <a href="${site.url}" class="alert-link">${site.url}</a>
                    </div>
                </td>
                <td>
                    <c:if test="${site.monitoringActive=='true'}">
                        <div class="passing">
                            <span>
                            <ion-icon size="large" name="search"></ion-icon>
                        </span>
                        </div>
                    </c:if>
                    <c:if test="${site.monitoringActive=='false'}">
                        <div class="pulse">
                            <ion-icon name="remove-circle" size="large"></ion-icon>
                        </div>
                    </c:if>
                </td>
                <td style="vertical-align: middle;"><span>${site.seconds}</span></td>
                <td style="vertical-align: middle;"><span>${site.min}-${site.max}</span>
                </td>
                <td style="vertical-align: middle;">
                    <c:if test="${site.status=='OK'}">
                        <div class="float">
                    <span>
                            ${site.status}
                            <ion-icon size="large" name="checkmark-circle"> ${site.status}</ion-icon>
                    </span>
                        </div>
                    </c:if>
                    <c:if test="${site.status=='WARNING'}">
                        <div class="double-flash">
                    <span>
                            ${site.status}
                            <ion-icon size="large" name="bulb"> ${site.status}</ion-icon>
                    </span>
                        </div>
                    </c:if>
                    <c:if test="${site.status=='CRITICAL'}">
                        <div class="critical">
                    <span>
                            ${site.status}
                    </span>
                        </div>
                    </c:if>
                </td>
                <td>
                    <div class="row">
                        <button class="btn btn-warning" type="button" data-toggle="collapse"
                                data-target="#collapseEdit_${site.id}" aria-expanded="false"
                                aria-controls="collapseExample">
                            edit
                        </button>
                        <form method="POST" action="/activate">
                        <c:if test="${site.monitoringActive=='true'}">
                            <button type="submit" value="${site.id}" name="action" class="btn btn-outline-dark">
                                deactivate
                            </button>
                        </c:if>
                        <c:if test="${site.monitoringActive=='false'}">
                            <button type="submit" value="${site.id}" name="action" class="btn btn-outline-dark">
                                Active
                            </button>
                        </c:if>
                    </form>
                        <form method="POST" action="/action">
                            <button type="submit" class="btn btn-danger" value="${site.id}" name="action">delete
                            </button>
                        </form>


                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="7">

                    <div class="collapse" id="collapseEdit_${site.id}">
                        <div class="card card-body">
                            <form class="form-horizontal" method="POST" action="/editSite">

                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">edit monitoring period</span>
                                    </div>
                                    <input type="text" aria-label="hour" class="form-control" placeholder="hour"
                                           name="hours"
                                           value="${siteModel.hours }"/>
                                    <input type="text" aria-label="minutes" class="form-control" placeholder="minutes"
                                           name="minutes" value="${siteModel.minutes }"/>
                                    <input type="text" aria-label="seconds" class="form-control" placeholder="seconds"
                                           name="seconds" value="${siteModel.seconds }"/>
                                </div>

                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">edit min</span>
                                    </div>
                                    <input type="text" class="form-control" name="min" value="${siteModel.min }"/>
                                    <input type="text" class="form-control" name="max" value="${siteModel.max }"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text">edit max</span>
                                    </div>
                                </div>

                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"
                                              id="substringEdit">enter your expected substring</span>
                                    </div>
                                    <input type="text" class="form-control" aria-describedby="substringEdit"
                                           name="substringInResponse" value="${siteModel.substringInResponse }"/>
                                </div>

                                <div class="wrapper">
                                    <button type="submit" name="action" value="${site.id}" class="btn btn-primary">
                                        edit site
                                    </button>
                                </div>
                            </form>

                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="wrapper">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">add site
        </button>
    </div>
    <div id="modal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
         aria-labelledby="myLargeModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="container" style="min-height: 50vh">

                    <h1 align="center">
                        form create
                    </h1>
                    <form class="form-horizontal" method="POST" action="/addSite">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon3">Url</span>
                            </div>
                            <input type="text" class="form-control" id="basic-url"
                                   placeholder="Recipient's username"
                                   aria-describedby="basic-addon3" name="url" value="${siteModel.url}"/>
                        </div>

                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">monitoring period</span>
                            </div>
                            <input type="text" aria-label="hour" class="form-control" placeholder="hour" name="hours"
                                   value="${siteModel.hours }"/>
                            <input type="text" aria-label="minutes" class="form-control" placeholder="minutes"
                                   name="minutes" value="${siteModel.minutes }"/>
                            <input type="text" aria-label="seconds" class="form-control" placeholder="seconds"
                                   name="seconds" value="${siteModel.seconds }"/>
                        </div>

                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">min</span>
                            </div>
                            <input type="text" class="form-control" name="min" value="${siteModel.min }"/>
                            <input type="text" class="form-control" name="max" value="${siteModel.max }"/>
                            <div class="input-group-append">
                                <span class="input-group-text">max</span>
                            </div>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="substring">enter your expected substring</span>
                            </div>
                            <input type="text" class="form-control" aria-describedby="substring"
                                   name="substringInResponse" value="${siteModel.substringInResponse }"/>
                        </div>

                        <div class="wrapper">
                            <input type="submit" class="btn btn-primary" value="add site"/>
                        </div>
                    </form>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
</div>
<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })
</script>
<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>
<script>
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>
</body>

</html>