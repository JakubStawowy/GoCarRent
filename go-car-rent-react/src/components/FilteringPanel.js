import React, {useState} from 'react';
import {
    Container, Fab,
    Grid,
    MenuItem,
    Select,
    TextField,
} from "@material-ui/core";
import carBrands from "../data/carBrands";
import SearchIcon from "@material-ui/icons/Search";
import {getAnnouncements} from "../actions/actionRepository";
import ClearIcon from "@material-ui/icons/Clear";
import {useFilteringPanelStyles} from "../style/FilteringPanelStyles";

export default function FilteringPanel(props) {
    const classes = useFilteringPanelStyles();
    const [priceFrom, setPriceFrom] = useState('');
    const [priceTo, setPriceTo] = useState('');
    const [timeUnit, setTimeUnit] = useState('');
    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');
    const [status, setStatus] = useState('');

    const validatePrice = (price) => {
        return /^\d+\.?\d$/.test(price);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        props.action1();
        let body = [];
        if (validatePrice(priceTo)) {
            body.push({
                key: "amount",
                operation: "<=",
                value: priceTo
            });
        }

        if (validatePrice(priceFrom)) {
            body.push({
                key: "amount",
                operation: ">=",
                value: priceFrom
            });
        }

        if (timeUnit !== '') {
            body.push({
               key: "timeUnit",
               operation: "=",
               value: timeUnit
            });
        }

        if (status !== '') {
            body.push({
                key: "rentStatus",
                operation: "=",
                value: status
            });
        }

        if (brand !== '') {
            body.push({
                key: "carBrand",
                operation: "=",
                value: brand
            });
        }

        if (model !== '') {
            body.push({
               key: "carModel",
               operation: "=",
               value: model
            });
        }

        getAnnouncements(body).then((response) => {
            props.action2(response.data);
        }).catch((error) => alert(error));
    }

    return (
        <Container className={classes.container}>
            <form className={classes.form} onSubmit={handleSubmit}>
                <Grid container justify={'center'} className={classes.gridContainer}>
                    <Grid item xs={5}>
                        <TextField
                            type={'text'}
                            label={'Price from'}
                            className={classes.item}
                            value={priceFrom}
                            onChange={(e) => setPriceFrom(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <TextField
                            type={'text'}
                            label={'Price to'}
                            className={classes.item}
                            value={priceTo}
                            onChange={(e) => setPriceTo(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            label={'TimeUnit'}
                            value={timeUnit}
                            className={classes.item}
                            onChange={(e) => setTimeUnit(e.target.value)}
                        >
                            <MenuItem value={"HOURS"}>
                                per hour
                            </MenuItem>
                            <MenuItem value={"DAYS"}>
                                per day
                            </MenuItem>
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={brand}
                            onChange={(e) => setBrand(e.target.value)}
                        >
                            {
                                carBrands.map(brand => {
                                    return (
                                        <MenuItem selected={false} value={brand.brand}>{brand.brand}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={model}
                            onChange={(e) => setModel(e.target.value)}
                        >
                            {
                                carBrands[0].models.map(model => {
                                    return (
                                        <MenuItem value={model}>{model}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={status}
                            onChange={(e) => setStatus(e.target.value)}
                        >
                            {
                                localStorage.getItem('role') === 'ROLE_ADMIN' &&
                                <MenuItem selected={false} value={'BLOCKED'}>Blocked</MenuItem>
                            }
                            <MenuItem selected={false} value={'FREE'}>Free</MenuItem>
                            <MenuItem selected={false} value={'RENTED'}>Rented</MenuItem>
                            <MenuItem selected={false} value={'RETURNED'}>Returned</MenuItem>

                        </Select>

                    </Grid>

                    <Grid item xs={5}
                          className={classes.buttonGridItem}>
                        <Fab variant={'extended'} className={classes.button} type={"submit"}>
                            Search
                            <SearchIcon fontSize={"large"}/>
                        </Fab>
                    </Grid>
                    <Grid item xs={5}
                          className={classes.buttonGridItem}>
                        <Fab variant={'extended'} className={classes.button} onClick={props.action1}>
                            Cancel
                            <ClearIcon fontSize={"large"}/>
                        </Fab>
                    </Grid>
                </Grid>
            </form>
        </Container>
    );
}