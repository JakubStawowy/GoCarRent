import React from 'react';
import '../components/components.css';
import {
    Button,
    Container, Fab,
    makeStyles, Paper, Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow, Typography,
} from "@material-ui/core";

import rentedCars from "../data/rentedCars";
import CheckIcon from '@material-ui/icons/Check';
import ClearIcon from '@material-ui/icons/Clear';

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
                            rentedCars.map(car => {
                                let statusComponent;
                                switch (car.status) {
                                    case 'rented':
                                        statusComponent = (
                                            <Fab variant={"extended"}>
                                                Return
                                            </Fab>
                                        );
                                        break;
                                    case 'waiting':
                                        statusComponent = (
                                            <Fab variant={"extended"} className={classes.waiting}>
                                                Waiting
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
                                        <TableCell align={"center"}>{car.brand}</TableCell>
                                        <TableCell align={"center"}>{car.model}</TableCell>
                                        <TableCell align={"center"}>{car.price}</TableCell>
                                        <TableCell align={"center"}>{car.time}</TableCell>
                                        <TableCell align={"center"}>{car.fee}</TableCell>
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