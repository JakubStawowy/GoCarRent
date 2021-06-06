import React, {useEffect, useState} from 'react';
import {
    Container, Fab,Paper, Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from "@material-ui/core";
import {useHistory} from "react-router";
import {deleteRent, getTenantRents, sendRentReturnProcessMessage} from "../actions/actionRepository";
import {REQUEST_FOR_RENT_RETURN} from "../data/messageTypes";
import {useRentedCarsStyles} from "../style/RentedCarsStyles";


export default function RentedCars() {
    const classes = useRentedCarsStyles();
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

    const handleReturnRequest = (rentId) => {
        sendRentReturnProcessMessage({
            messageType: REQUEST_FOR_RENT_RETURN,
            tenantId: localStorage.getItem('userId'),
            rentId: rentId
        }).then(()=>alert("Request for rent return sent successfully"))
            .catch((error)=>alert(error));
    }

    const handleDeleteRent = (rentId) => {
        deleteRent(rentId)
            .then(()=>alert("Rent deleted successfully"))
            .catch((error)=>alert(error));
    }

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
                            <TableCell align={"center"}>Status</TableCell>
                            <TableCell align={"center"}>Action</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            rentedCars.map(rent => {
                                return (
                                    <TableRow>
                                        <TableCell align={"center"}>{rent.announcement.carBrand}</TableCell>
                                        <TableCell align={"center"}>{rent.announcement.carModel}</TableCell>
                                        <TableCell align={"center"}>{rent.announcement.amount}</TableCell>
                                        <TableCell align={"center"}>{rent.rentTime} {rent.announcement.timeUnit.substr(0, 1)}</TableCell>
                                        <TableCell align={"center"}>{rent.fee} {rent.announcement.currency}</TableCell>
                                        <TableCell align={"center"}>
                                            {
                                                rent.rentStatus === 'ON_GOING' ?
                                                    <Fab variant={"extended"} className={classes.rented}>
                                                        on going
                                                    </Fab>
                                                    :
                                                    <Fab variant={"extended"} className={classes.returned}>
                                                        finished
                                                    </Fab>
                                            }
                                        </TableCell>
                                        <TableCell align={"center"}>
                                            {
                                                rent.rentStatus === 'ON_GOING' ?
                                                <Fab variant={"extended"} onClick={()=> handleReturnRequest(rent.rentId)}>
                                                    Return
                                                </Fab>
                                                :
                                                <Fab variant={"extended"} onClick={()=>handleDeleteRent(rent.rentId)}>
                                                    Delete
                                                </Fab>
                                            }
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