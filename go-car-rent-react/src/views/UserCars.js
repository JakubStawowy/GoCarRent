import React, {useEffect, useState} from 'react';
import {
    Container, Paper, Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from "@material-ui/core";

import CheckIcon from '@material-ui/icons/Check';
import ClearIcon from '@material-ui/icons/Clear';
import {getUserAnnouncements} from "../actions/actionRepository";
import {useHistory} from "react-router";
import {useUserCarsStyles} from "../style/UserCarsStyles";

export default function UserCars() {
    const classes = useUserCarsStyles();
    const history = useHistory();
    const [userCars, setUserCars] = useState([]);
    useEffect(() => {
        getUserAnnouncements(localStorage.getItem('userId')).then((response) => {
            setUserCars(response.data);
        }).catch(() => {
            localStorage.clear();
            history.replace("/login");
        });
    }, []);

    return (
        <Container className={classes.container}>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead className={classes.tableHead}>
                        <TableRow>
                            <TableCell align={"center"}>Brand</TableCell>
                            <TableCell align={"center"}>Model</TableCell>
                            <TableCell align={"center"}>Price</TableCell>
                            <TableCell align={"center"}>Rented</TableCell>
                            <TableCell align={"center"}>Time</TableCell>
                            <TableCell align={"center"}>Fee</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            userCars.map(car => {
                                return (
                                  <TableRow>
                                      <TableCell align={"center"}>{car.carBrand}</TableCell>
                                      <TableCell align={"center"}>{car.carModel}</TableCell>
                                      <TableCell align={"center"}>{car.amount} {car.currency} / {car.timeUnit.substr(0, 1)}</TableCell>
                                      {
                                          car.status === 'RENTED' ?
                                              <TableCell align={"center"}>
                                                  <CheckIcon className={classes.check}/>
                                              </TableCell>
                                              :
                                              <TableCell align={"center"}>
                                                  <ClearIcon className={classes.clear}/>
                                              </TableCell>
                                      }
                                      <TableCell align={"center"}>12</TableCell>
                                      <TableCell align={"center"}>34</TableCell>
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