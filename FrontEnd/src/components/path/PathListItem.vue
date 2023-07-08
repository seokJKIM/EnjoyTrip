<template>
    <b-tr class="item-row">
        <b-td> {{ index+1 }}</b-td>
        <b-td>{{ tour.title }}</b-td>
        <b-td v-b-hover="handleHover">
            <b-icon v-if="isHovered" icon="x-circle-fill" scale="2" variant="danger" @click="deleteTour()"></b-icon>
            <b-icon v-else icon="x-circle" scale="2" variant="danger"></b-icon>
        </b-td>
    </b-tr>
</template>
  
<script>
export default {
    name: "PathListItem",
    props: {
        index: Number,
        tour: Object,
    },
    components: {},
    data() {
        return {
            isHovered: false,
        };
    },
    created() {},
    methods: {
        // PlaceList에 emit으로 place 전달
        deleteTour() {
            if(this.isHovered){
                console.log("deleteItem", this.index);
                this.$emit("deleteItem", this.index);
            }
        },
        handleHover(hovered){
            this.isHovered = hovered;
        }
    },
};
</script>

<style scoped>
.item-row {
  cursor: pointer;
}
</style>
