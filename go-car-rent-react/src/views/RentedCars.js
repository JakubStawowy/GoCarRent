import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {
    Container, Fab,
    makeStyles, Paper, Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from "@material-ui/core";
import {useHistory} from "react-router";
import {getUserAnnouncements} from "../actions/getUserAnnouncements";
import {getTenantRents} from "../actions/getTenantRents";

const useStyles = makeStyles((theme) => ({
    tableHead: {
        background: '#4BBEBAE0',
    },
    container: {
        padding: '1em'
    },
    check: {
        color: "green"
    },
    clear: {
        color: "red"
    },
    table: {
        height: "10vh"
    },
    waiting: {
        background: "#FFD700"
    },
    returned: {
        background: "#32CD32"
    }
}));

export default function RentedCars() {
    const classes = useStyles();
    const history = useHistory();
    const [rentedCars, setRentedCars] = useState([]);

    useEffect(() => {
        getTenantRents(localStorage.getItem('userId')).then((response) => {
            setRentedCars(response.data);
        }).catch(() => {
            localStorage.clear();
            history.replace("/login");
        });
    }, []);

    return (
        <Container className={classes.container}>
            <TableContainer component={Paper}>
                <Table className={classes.table}>
                    <TableHead className={classes.tableHead}>
                        <TableRow>
                            <TableCell align={"center"}>Brand</TableCell>
                            <TableCell align={"center"}>Model</TableCell>
                            <TableCell align={"center"}>Price</TableCell>
                            <TableCell align={"center"}>Time</TableCell>
                            <TableCell align={"center"}>Fee</TableCell>
                            <TableCell align={"center"}>Action/Status</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            rentedCars.map(rent => {
                                let statusComponent;
                                switch (rent.announcement.status) {
                                    case 'RENTED':
                                        statusComponent = (
                                            <Fab variant={"extended"}>
                                                Rented
                                            </Fab>
                                        );
                                        break;
                                    case 'RETURNED':
                                        statusComponent = (
                                            <Fab variant={"extended"} className={classes.waiting}>
                                                Returned
                                            </Fab>
                                        );
                                        break;
                                    default:
                                        statusComponent = (
                                            <Fab variant={"extended"} className={classes.returned}>
                                                Returned
                                            </Fab>
                                        );
                                }
                                return (
                                    <TableRow>
                                        <TableCell align={"center"}>{rent.announcement.carBrand}</TableCell>
                                        <TableCell align={"center"}>{rent.announcement.carModel}</TableCell>
                                        <TableCell align={"center"}>{rent.announcement.amount}</TableCell>
                                        <TableCell align={"center"}>20</TableCell>
                                        <TableCell align={"center"}>30</TableCell>
                                        <TableCell align={"center"}>
                                            {statusComponent}
                                        </TableCell>
                                    </TableRow>
                                );
                            })
                        }
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    );
}