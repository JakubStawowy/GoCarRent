import React, {useState} from 'react';
import '../components/components.css';
import {
    Button,
    Card,
    Container, Fab,
    FormControl, Grid,
    makeStyles,
    MenuItem,
    Select, TextareaAutosize,
    TextField,
    Typography
} from "@material-ui/core";
import PublishIcon from '@material-ui/icons/Publish';
import RoomIcon from '@material-ui/icons/Room';
import carBrands from "../data/carBrands";
import ImageIcon from '@material-ui/icons/Image';
import {useDispatch} from "react-redux";
import {addAnnouncement} from "../actions/addAnnouncement";

const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
        justifyContent: 'space-around'
    },
    form: {
        height: '70%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
    },
    item: {
        width: '90%'
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '80%',
        borderRadius: '2em'
    },
    button: {
        background: '#4BBEBAE0',
    },
    titleField: {
        marginTop: '2em'
    }
}));

export default function Add() {
    const classes = useStyles();

    const [title, setTitle] = useState('');
    const [price, setPrice] = useState('');
    const [timeUnit, setTimeUnit] = useState('');
    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');

    const dispatch = useDispatch();
    const handleSubmit = (e) => {
        e.preventDefault();

        const data = {
            "title": title,
            "amount": price,
            "currency": "PLN",
            "timeUnit": timeUnit,
            "carBrand": brand,
            "carModel": model,
            "authorId": localStorage.getItem("userId")
        };

        dispatch(addAnnouncement(data)).then(
            () => alert('Announcement added successfully')
        ).catch(
            (error) => alert(error)
        );
    }

    return (
        <Container className={classes.container}>
            <Typography variant={'h4'} align={'center'}>
                Add announcement
            </Typography>
            <form className={classes.form} onSubmit={handleSubmit}>
                <Grid container justify={'center'} className={classes.gridContainer}>
                    <Grid item xs={10} className={classes.titleField}>
                        <TextField
                            type={'text'}
                            label={'Title'}
                            className={classes.item}
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <TextField
                            type={'text'}
                            label={'Price'}
                            className={classes.item}
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={timeUnit}
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
                        <Button className={classes.item}>
                            Localisation
                            <RoomIcon />
                        </Button>
                    </Grid>
                    <Grid item xs={5}>
                        <Button className={classes.item}>
                            Image
                            <ImageIcon />
                        </Button>
                    </Grid>
                </Grid>
                <Fab variant={'extended'} className={classes.button} type={"submit"}>
                    Publish
                    <PublishIcon />
                </Fab>
            </form>
        </Container>
    );
}