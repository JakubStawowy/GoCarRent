import React, {Component} from 'react';
import '../components/components.css';
import {
    Container,
    Grid,
    makeStyles, Paper, Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@material-ui/core";

import userCars from "../data/userCars";
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
    }
}));

export default function UserCars() {
    const classes = useStyles();
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
                                      <TableCell align={"center"}>{car.brand}</TableCell>
                                      <TableCell align={"center"}>{car.model}</TableCell>
                                      <TableCell align={"center"}>{car.price}</TableCell>
                                      {
                                          car.rented ?
                                              <TableCell align={"center"}>
                                                  <CheckIcon className={classes.check}/>
                                              </TableCell>
                                              :
                                              <TableCell align={"center"}>
                                                  <ClearIcon className={classes.clear}/>
                                              </TableCell>
                                      }
                                      <TableCell align={"center"}>{car.time}</TableCell>
                                      <TableCell align={"center"}>{car.fee}</TableCell>
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